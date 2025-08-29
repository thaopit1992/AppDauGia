package com.example.appdaugia.service.response

import com.example.appdaugia.service.RetrofitClient
import com.example.appdaugia.service.RetrofitClient.api
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.google.gson.Gson

class AuthRepository {
    suspend fun login(request: LoginRequest): Result<ApiResponse<LoginData>> {
        return try {
            val response = api.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getUser(token: String?): Result<ApiResponse<LoginData>> {
        return try {
            val response = api.getUser(token)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(request: RegisterRequest): Result<BaseResponse>  {
        return try {
            val response = api.register(request)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                // parse lỗi
                val errorBody = response.errorBody()?.string()
                val errorResponse = try {
                    Gson().fromJson(errorBody, BaseResponse::class.java)
                } catch (e: Exception) {
                    null
                }
                if (errorResponse != null) {
                    Result.success(errorResponse) // Trả về BaseResponse lỗi
                } else {
                    Result.failure(Exception("Unexpected error: $errorBody"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun forgot(request: ForgotRequest): Result<BaseResponse> {
        return try {
            val response = api.forgot(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}