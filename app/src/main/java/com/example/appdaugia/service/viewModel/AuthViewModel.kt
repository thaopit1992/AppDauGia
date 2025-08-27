package com.example.appdaugia.service.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.AuthRepository
import com.example.appdaugia.service.response.LoginResponse

class AuthViewModel  : ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<Result<ApiResponse<LoginResponse>>>()
    val loginResult: LiveData<Result<ApiResponse<LoginResponse>>> = _loginResult

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
}