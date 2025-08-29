package com.example.appdaugia.service.response

data class BaseResponse(
    val status: Int,
    val code: String?,
    val message: String?,
    val errors: Map<String, List<String>>? = null
)