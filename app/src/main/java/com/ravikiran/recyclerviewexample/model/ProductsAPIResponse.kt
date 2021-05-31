package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName

data class ProductsAPIResponse(
    // getting response data from rest api
    @SerializedName("products")
    val products: List<LatestProducts>,
    @SerializedName("cartcount")
    val totalResults: Int
)
