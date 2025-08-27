package com.example.appdaugia.service.response

data class ApiResponse<T>(
    val status: Int,
    val code: String,
    val message: String,
    val token : String,
    val data: T? = null,
)