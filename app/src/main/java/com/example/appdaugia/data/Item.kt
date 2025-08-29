package com.example.appdaugia.data

import java.io.Serializable

data class Item (
    val id: String,
    val order_id: String,
    val product_id: String,
    val product_cost: String,
    val quantity: String,
    val product_price_net: String,
    val vat: String,
    val total_price: String,
    val product_price_vat: String,
    val product: Product
) : Serializable