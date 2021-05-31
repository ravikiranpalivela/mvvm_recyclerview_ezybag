package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import java.io.Serializable

data class LatestProducts(

    // getting Latest products data
    @SerializedName("prod_id")
    val prod_id: String?,

    @SerializedName("prod_name")
    val prod_name: String?,

    @SerializedName("subtitle")
    val subtitle: String?,

    @SerializedName("minititle")
    val minititle: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("qty")
    var qty: List<String>?,

    @SerializedName("discount")
    var discount: List<String>?,

    @SerializedName("mrpprice")
    var mrpprice: List<String>?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("cat_id")
    val cat_id: String?,

    @SerializedName("subcat_id")
    val subcat_id: String?,

    @SerializedName("brand_id")
    val brand_id: String?,

    @SerializedName("price")
    var price: List<String>?,

    @SerializedName("priceoff")
    val priceoff: List<String>?,

    ) : Serializable