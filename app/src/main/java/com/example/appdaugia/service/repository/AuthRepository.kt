package com.example.appdaugia.service.repository

import com.example.appdaugia.service.RetrofitClient
import com.example.appdaugia.service.request.ChangePassRequest
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.BaseResponse
import com.example.appdaugia.service.response.ListCoutryResponse
import com.example.appdaugia.service.response.LoginData
import com.google.gson.Gson

class AuthRepository {
    suspend fun login(request: LoginRequest): Result<BaseResponse> {
        return try {
            val response = RetrofitClient.api.login(request)
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
    suspend fun getUser(token: String?): Result<BaseResponse> {
        return try {
            val response = RetrofitClient.api.getUser(token)
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

    suspend fun register(request: RegisterRequest): Result<BaseResponse>  {
        return try {
            val response = RetrofitClient.api.register(request)

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
            val response = RetrofitClient.api.forgot(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun changePass(request: ChangePassRequest): Result<BaseResponse> {
        return try {
            val response = RetrofitClient.api.changePass(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun editeUser(request: RegisterRequest): Result<BaseResponse> {
        return try {
            val response = RetrofitClient.api.editeUser(request)

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
                    Result.success(errorResponse)
                } else {
                    Result.failure(Exception("Unexpected error: $errorBody"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getListCountry(token : String): Result<ListCoutryResponse> {
        return try {
            val response = RetrofitClient.api.getListCountry(token)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                // parse lỗi
                val errorBody = response.errorBody()?.string()
                val errorResponse = try {
                    Gson().fromJson(errorBody, ListCoutryResponse::class.java)
                } catch (e: Exception) {
                    null
                }
                if (errorResponse != null) {
                    Result.success(errorResponse)
                } else {
                    Result.failure(Exception("Unexpected error: $errorBody"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}