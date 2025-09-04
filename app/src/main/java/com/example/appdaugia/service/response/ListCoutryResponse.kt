package com.example.appdaugia.service.response

import com.example.appdaugia.data.CountryData

data class ListCoutryResponse (
    val status: Int,
    val code: String?,
    val message: String?,
    val token:String?,
    val data: List<CountryData>? = null,
    val errors: Map<String, List<String>>? = null
)