package com.example.appdaugia.service.request

data class ChangePassRequest(
    val token: String?,
    val password: String?,
    val password_confirmation: String?
)