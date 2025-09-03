package com.example.appdaugia.service.response

data class BaseResponse(
    val status: Int,
    val code: String?,
    val message: String?,
    val token:String?,
    val data: LoginData? = null,
    val errors: Map<String, List<String>>? = null
)