package com.example.appdaugia.data

import java.io.Serializable

data class CountryData (
    val id: Int?,
    val name: String?,
    val code: String?,
    val slug: String?
) : Serializable