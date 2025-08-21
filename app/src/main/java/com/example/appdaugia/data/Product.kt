package com.example.appdaugia.data

import java.io.Serializable

data class Product (
    val name: String,
    val price: String,
    val quantity: String,
    val net: String,
    val vat: String,
    val gross: String,
) : Serializable