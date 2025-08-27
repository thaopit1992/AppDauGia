package com.example.appdaugia.service.response

import com.example.appdaugia.service.RetrofitClient
import com.example.appdaugia.service.request.LoginRequest

class AuthRepository {
    suspend fun login(request: LoginRequest): Result<ApiResponse<LoginResponse>> {
        return try {
            // Gọi API qua RetrofitClient
            val response = RetrofitClient.api.login(request)

            // Trả về kết quả thành công
            Result.success(response)
        } catch (e: Exception) {
            // Trả về lỗi nếu API thất bại
            Result.failure(e)
        }
    }
}