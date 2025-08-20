package com.example.appdaugia.data

import java.io.Serializable

data class ListItem (
    val id_order: String,
    val time: String,
    val number: String,
    val total: String,
    val status: String,
) : Serializable