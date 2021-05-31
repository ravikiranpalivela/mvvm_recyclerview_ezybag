package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName

data class SubCatAPIResponse(
    // getting response data from rest api
    @SerializedName("subcategorysliders")
    val sliders: List<Sliders>,
    @SerializedName("Latest products")
    val latest_products: List<LatestProducts>,
    @SerializedName("subcategory")
    val category: List<SubCategory>,
    @SerializedName("cartcount")
    val totalResults: Int
)
