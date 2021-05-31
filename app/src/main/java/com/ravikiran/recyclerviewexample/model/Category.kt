package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(

// getting Category data
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("subtitle")
    val subtitle: String?,

    @SerializedName("description")
    val description: String?,


    @SerializedName("img")
    val img: String?,

    @SerializedName("created_date")
    val created_date: String?

) : Serializable