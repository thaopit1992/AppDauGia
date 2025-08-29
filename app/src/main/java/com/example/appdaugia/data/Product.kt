package com.example.appdaugia.data

import java.io.Serializable

data class Product (
    val id: String,
    val name: String,
    val price: String,
    val updated_at: String
) : Serializable