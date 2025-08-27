package com.example.appdaugia.service.request

data class RegisterRequest(
    val token: String,
    val name: String,
    val first_name: String,
    val company: String,
    val vat_id: String,
    val street: String,
    val house_number: String,
    val postal_code: String,
    val location: String,
    val country: String,
    val full_name_rec: String,
    val street_rec : String,
    val house_number_rec: String,
    val postal_code_rec: String,
    val location_rec :String,
    val country_rec : String,
    val phone: String,
    val email : String,
    val tiktok_username: String,
    val password : String,
    val password_confirmation: String
)