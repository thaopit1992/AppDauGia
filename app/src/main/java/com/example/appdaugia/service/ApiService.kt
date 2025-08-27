package com.example.appdaugia.service

import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>

//    @GET("users")
//    fun getUsers(): Call<List<User>>

//    @POST("users")
//    fun createUser(@Body user: User): Call<User>
}