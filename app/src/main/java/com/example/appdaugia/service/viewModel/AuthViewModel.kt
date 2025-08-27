package com.example.appdaugia.service.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.AuthRepository
import com.example.appdaugia.service.response.LoginData
import com.example.appdaugia.service.response.Response

class AuthViewModel  : ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<Result<ApiResponse<LoginData>>>()
    private val _registerResult = MutableLiveData<Result<Response>>()
    val loginResult: LiveData<Result<ApiResponse<LoginData>>> = _loginResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    fun login(token: String, username: String, password: String) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val request = LoginRequest(token, username, password)
            val result = repository.login(request)
            _loginResult.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }
    fun getUser(token: String?) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val result = repository.getUser(token)
            _loginResult.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }
    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val result = repository.register(request)
            _registerResult.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }
    fun forgot(request: ForgotRequest) {
        viewModelScope.launch {
            _loading.postValue(true) // 👉 Bật loading
            val result = repository.forgot(request)
            _registerResult.postValue(result)
            _loading.postValue(false) // 👉 Tắt loading
        }
    }
}