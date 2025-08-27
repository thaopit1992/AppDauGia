package com.example.appdaugia.service.request

data class ForgotRequest(
    val token: String?,
    val email: String
)