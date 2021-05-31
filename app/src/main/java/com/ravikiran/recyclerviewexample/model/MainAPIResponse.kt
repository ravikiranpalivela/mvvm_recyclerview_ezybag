package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName

data class MainAPIResponse(
    // getting response data from rest api
    @SerializedName("Sliders")
    val sliders: List<Sliders>,
    @SerializedName("Latest products")
    val latest_products: List<LatestProducts>,
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("cartcount")
    val totalResults: Int
)
