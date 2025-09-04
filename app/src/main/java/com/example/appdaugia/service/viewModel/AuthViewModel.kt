package com.example.appdaugia.service.viewModel

import android.Manifest
import android.R.id.message
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.repository.AuthRepository
import com.example.appdaugia.service.request.ChangePassRequest
import com.example.appdaugia.service.response.BaseResponse
import com.example.appdaugia.service.response.ListCoutryResponse
import com.example.appdaugia.service.response.LoginData
import com.example.appdaugia.utils.DialogUtils
import com.example.appdaugia.utils.NetworkUtils

class AuthViewModel  : ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<Result<BaseResponse>>()
    val loginResult: LiveData<Result<BaseResponse>> = _loginResult

    private val _baseResult = MutableLiveData<Result<BaseResponse>>()
    val baseResult: LiveData<Result<BaseResponse>> = _baseResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _editUserResult = MutableLiveData<Result<BaseResponse>>()
    val editUserResult: LiveData<Result<BaseResponse>> = _editUserResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun login(context: Context, token: String, username: String, password: String) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            try {
                val request = LoginRequest(token, username, password)
                val result = repository.login(request)
                result.onSuccess { baseResponse ->
                    if (baseResponse.status == 1) {
                        // Thành công
                        _loginResult.postValue(result)
                    } else {
                        // Có lỗi -> gom tất cả thông báo
                        _errorMessage.postValue(baseResponse.getAllErrors())
                    }
                }.onFailure { e ->
                    _errorMessage.postValue(e.message ?: "Unknown error")
                }
            } finally {
                _loading.postValue(false)
            }
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getUser(context: Context, token: String?) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
            viewModelScope.launch {
                try {
                    val result = repository.getUser(token)
                    result.onSuccess { baseResponse ->
                        if (baseResponse.status == 1) {
                            // Thành công
                            _loginResult.postValue(result)
                        } else {
                            // Có lỗi -> gom tất cả thông báo
                            _errorMessage.postValue(baseResponse.getAllErrors())
                        }
                    }.onFailure { e ->
                        _errorMessage.postValue(e.message ?: "Unknown error")
                    }
                } finally {
                    _loading.postValue(false)
                }
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun register(context: Context,request: RegisterRequest) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            try {
                val result = repository.register(request)
                result.onSuccess { baseResponse ->
                    if (baseResponse.status == 1) {
                        // Thành công
                        _baseResult.postValue(result)
                    } else {
                        // Có lỗi -> gom tất cả thông báo
                        _errorMessage.postValue(baseResponse.getAllErrors())
                    }
                }.onFailure { e ->
                    _errorMessage.postValue(e.message ?: "Unknown error")
                }
            } finally {
                _loading.postValue(false)
            }
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun forgot(context: Context, request: ForgotRequest) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            try {
                val result = repository.forgot(request)
                _baseResult.postValue(result)
            } catch (e: Exception) {
                _baseResult.postValue(Result.failure(e))
            } finally {
                _loading.postValue(false) // 👉 Tắt loading
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun chnagePass(context: Context, request: ChangePassRequest) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            try {
                val result = repository.changePass(request)
                _baseResult.postValue(result)
            } catch (e: Exception) {
                _baseResult.postValue(Result.failure(e))
            } finally {
                _loading.postValue(false) // 👉 Tắt loading
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun editeUser(context: Context, request: RegisterRequest) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu
        viewModelScope.launch {
            try {
                val result = repository.editeUser(request)
                result.onSuccess { baseResponse ->
                    if (baseResponse.status == 1) {
                        _editUserResult.postValue(result)
                    } else {
                        _errorMessage.postValue(baseResponse.getAllErrors())
                    }
                }.onFailure { e ->
                    _errorMessage.postValue(e.message ?: "Unknown error")
                }
            } finally {
                _loading.postValue(false)
            }
        }
    }

    private val _getListCountryResult = MutableLiveData<Result<ListCoutryResponse>>()
    val getListCountryResult: LiveData<Result<ListCoutryResponse>> = _getListCountryResult
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getListCountry(context: Context, token: String) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            DialogUtils.showCustomDialog(
                context = context,
                message = "No Internet",
            ) {}
            return
        }
        _loading.postValue(true)   // bắt đầu

        viewModelScope.launch {
            try {
                val result = repository.getListCountry(token)
                result.onSuccess { baseResponse ->
                    if (baseResponse.status == 1) {
                        _getListCountryResult.postValue(result)
                    } else {
                        _errorMessage.postValue(baseResponse.getAllErrors())
                    }
                }.onFailure { e ->
                    _errorMessage.postValue(e.message ?: "Unknown error")
                }
            } finally {
                _loading.postValue(false)
            }
        }
    }
    // ham lay loi
    fun BaseResponse.getAllErrors(): String {
        val builder = StringBuilder()
        // Thêm message gốc nếu có
        message?.let {
            builder.append( "- ")
            builder.append(it)
            builder.append("\n")
        }
        // Duyệt errors động
        errors?.forEach { (_, messages) ->
            messages.forEach { msg ->
                builder.append( "- ")
                builder.append(msg)
                builder.append("\n")
            }
        }
        return builder.toString().trim()
    }
    fun ListCoutryResponse.getAllErrors(): String {
        val builder = StringBuilder()
        // Thêm message gốc nếu có
        message?.let {
            builder.append( "- ")
            builder.append(it)
            builder.append("\n")
        }
        // Duyệt errors động
        errors?.forEach { (_, messages) ->
            messages.forEach { msg ->
                builder.append( "- ")
                builder.append(msg)
                builder.append("\n")
            }
        }
        return builder.toString().trim()
    }
}

