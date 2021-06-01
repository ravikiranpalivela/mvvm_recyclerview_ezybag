package com.ravikiran.recyclerviewexample.model

import com.google.gson.annotations.SerializedName

data class User(

// getting User Details data
    @SerializedName("token")
    val token: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("phone")
    val phone: String?,


    @SerializedName("isregister")
    val isregister: Boolean?,


    @SerializedName("IsStatus")
    val IsStatus: Int?,


    @SerializedName("cartcount")
    val cartcount: String?,

    @SerializedName("password")
    val password: String?,


    @SerializedName("msg")
    val msg: String?,

    @SerializedName("otp")
    val otp: String?,


    )
