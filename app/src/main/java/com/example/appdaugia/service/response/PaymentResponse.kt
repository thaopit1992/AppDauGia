package com.example.appdaugia.service.response

import com.example.appdaugia.data.OrderData


data class PaymentResponse(
    val status: Int,
    val message: String?,
    val token:String?,
    val data: List<PayData>? = null,
    val errors: Map<String, List<String>>? = null
)