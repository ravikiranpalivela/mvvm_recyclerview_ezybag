package com.ravikiran.recyclerviewexample.model


import com.google.gson.annotations.SerializedName

data class AddtocartAPIResponse(

// getting User Details data
    @SerializedName("msg")
    val msg: String?,

    @SerializedName("cartcount")
    val cartcount: String?

)
