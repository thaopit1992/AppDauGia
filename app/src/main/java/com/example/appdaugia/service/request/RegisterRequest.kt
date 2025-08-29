package com.example.appdaugia.service.request

data class RegisterRequest(
    val token: String?,
    val name: String?,
    val first_name: String?,
    val company: String?,
    val vat_id: String?,
    val address: String?,
    val house_number: String?,
    val postal_code: String?,
    val location: String?,
    val country: String?,
    val phone: String?,
    val email : String?,
    val tiktok_username: String?,
    val password : String?,
    val password_confirmation: String?,
    val shipping_to: Int?,
    val shipping_fullname: String?,
    val shipping_house_number : String?,
    val shipping_address: String?,
    val shipping_postal_code : String?,
    val shipping_location: String?,
    val shipping_country: String?,
    val check_terms: Boolean = true
)