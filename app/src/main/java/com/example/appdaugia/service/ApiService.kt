package com.example.appdaugia.service

import com.example.appdaugia.data.OrderData
import com.example.appdaugia.service.request.ChangePassRequest
import com.example.appdaugia.service.request.ForgotRequest
import com.example.appdaugia.service.request.LoginRequest
import com.example.appdaugia.service.request.RegisterRequest
import com.example.appdaugia.service.response.ApiResponse
import com.example.appdaugia.service.response.BaseResponse
import com.example.appdaugia.service.response.DetailOrderResponse
import com.example.appdaugia.service.response.LoginData
import com.example.appdaugia.service.response.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json")
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginData>

    @Headers("Accept: application/json")
    @GET("api/users/information")
    suspend fun getUser(@Query ("token") token: String?): ApiResponse<LoginData>

    @Headers("Accept: application/json")
    @POST("api/users/register")
    suspend fun register(@Body request: RegisterRequest): Response<BaseResponse>
    @Headers("Accept: application/json")
    @POST("api/users/forgot-password")
    suspend fun forgot(@Body request: ForgotRequest): BaseResponse

    @Headers("Accept: application/json")
    @GET("/api/shop/my/order")
    suspend fun getListOrder(@Query ("token") token: String?): Response<OrderResponse>

    @Headers("Accept: application/json")
    @GET("api/shop/my/order/detail")
    suspend fun getDetail(@Query ("token") token: String?, @Query ("id_encode") id_encode: Long? ): Response<DetailOrderResponse>

    @Headers("Accept: application/json")
    @POST("api/users/change-password")
    suspend fun changePass(@Body request: ChangePassRequest): BaseResponse

    @Headers("Accept: application/json")
    @POST("/api/user/change-information")
    suspend fun editeUser(@Body request: RegisterRequest): Response<BaseResponse>
}