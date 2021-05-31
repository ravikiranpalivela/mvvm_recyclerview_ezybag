package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sliders(

// getting Sliders data
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_type")
    val image_type: String?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("now")
    val now: String?
) : Serializable