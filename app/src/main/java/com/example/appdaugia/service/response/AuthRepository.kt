package com.example.appdaugia.service.response

import com.example.appdaugia.service.RetrofitClient
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest

class AuthRepository {
    suspend fun login(request: LoginRequest): Result<ApiResponse<LoginData>> {
        return try {
            val response = RetrofitClient.api.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getUser(token: String?): Result<ApiResponse<LoginData>> {
        return try {
            val response = RetrofitClient.api.getUser(token)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    suspend fun register(request: RegisterRequest): Result<ForgotResponse> {
//        return try {
//            val response = RetrofitClient.api.createUser(request)
//            Result.success(response)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
suspend fun forgot(request: ForgotRequest): Result<BaseResponse> {
    return try {
        val response = RetrofitClient.api.forgot(request)
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
}