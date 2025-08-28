package com.example.appdaugia.service

import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.BaseResponse
import com.example.appdaugia.service.response.LoginData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginData>

    @GET("api/users/information")
    suspend fun getUser(@Query ("token") token: String?): ApiResponse<LoginData>

    @POST("api/users/register")
    fun createUser(@Body request: RegisterRequest): BaseResponse

    @POST("api/users/forgot-password")
    suspend fun forgot(@Body request: ForgotRequest): BaseResponse
}