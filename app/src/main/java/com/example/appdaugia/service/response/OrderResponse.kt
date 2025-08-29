package com.example.appdaugia.service.response

import com.example.appdaugia.data.OrderData


data class OrderResponse(
    val status: Int,
    val message: String,
    val data: List<OrderData>?
)