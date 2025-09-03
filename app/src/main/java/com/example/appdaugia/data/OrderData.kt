package com.example.appdaugia.data

import java.io.Serializable

data class OrderData (
    val id: Long?,
    val id_encode: Long?,
    val code: String?,
    val code_id: Long?,
    val studio_id: Long,
    val user_id: Long?,
    val items_price: String?,
    val items_count: Int?,
    val invoice_proforma_file: String?,
    val shipping_by: String?,
    val shipping_price: String?,
    val shipping_weight: Int?,
    val shipping_service: String?,
    val shipping_cost: String,
    val shipping_vat: String,
    val shipping_status: Int?,
    val shipping_shipment_id: Long?,
    val shipping_tracking_number: String?,
    val vat: String?,
    val vat_price: String?,
    val buy_date:String?,
    val payment_status : String?,
    val payment_service : String?,
    val total_price: String?,
    val total_price_net: String?,
    val items: List<Item>?
) : Serializable