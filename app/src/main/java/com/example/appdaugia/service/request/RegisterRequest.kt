package com.example.appdaugia.service.request

data class LoginRequest(
    val token: String,
    val email: String,
    val password: String
)