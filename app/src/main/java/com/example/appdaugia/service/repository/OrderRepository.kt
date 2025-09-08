package com.example.appdaugia.service.repository

import com.example.appdaugia.service.RetrofitClient
import com.example.appdaugia.service.response.DetailOrderResponse
import com.example.appdaugia.service.response.OrderResponse
import com.example.appdaugia.service.response.PaymentResponse

class OrderRepository {
    suspend fun getListOrder(token: String?): Result<OrderResponse> {
        return try {
            val response = RetrofitClient.api.getListOrder(token)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDetail(token: String?, id_encode: Long?): Result<DetailOrderResponse> {
        return try {
            val response = RetrofitClient.api.getDetail(token, id_encode)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBank(id: Long?, token: String?): Result<PaymentResponse> {
        return try {
            val response = RetrofitClient.api.getBank(id, token)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}