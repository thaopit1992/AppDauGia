package com.example.appdaugia.service.request

data class RegisterRequest(
    val token: String? = null,
    val name: String? = null,
    val first_name: String? = null,
    val company: String?= null,
    val vat_id: String?= null,
    val address: String?= null,
    val house_number: String?= null,
    val postal_code: String?= null,
    val location: String?= null,
    val country: String?= null,
    val phone: String?= null,
    val email : String?= null,
    val tiktok_username: String?= null,
    val password : String?= null,
    val password_confirmation: String?= null,
    val shipping_to: Int?= null,
    val shipping_fullname: String?= null,
    val shipping_house_number : String?= null,
    val shipping_address: String?= null,
    val shipping_postal_code : String?= null,
    val shipping_location: String?= null,
    val shipping_country: String?= null,
    val check_terms: Boolean? = true
)