package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubCategory(


// getting SubCategory data
    @SerializedName("id")
    val id: String?,

    @SerializedName("category_id")
    val category_id: String?,

    @SerializedName("quantity_type")
    val quantity_type: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("subtitle")
    val subtitle: String?,

    @SerializedName("description")
    val description: String?,


    @SerializedName("img")
    val img: String?,

    @SerializedName("status")
    val status: String?,


    @SerializedName("created_date")
    val created_date: String?

) : Serializable