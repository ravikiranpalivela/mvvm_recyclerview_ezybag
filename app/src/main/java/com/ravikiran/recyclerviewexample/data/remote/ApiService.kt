package com.ravikiran.recyclerviewexample.data.remote
import com.ravikiran.recyclerviewexample.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // getting data from api


    @GET("Mainpage")
    suspend fun getMainPageData(
        @Query("device_id")
        device_id: String,
        @Query("user_id")
        user_id: String
    ):
    Response<MainAPIResponse>

    @GET("Subcategory")
    suspend fun getSubcategory(
        @Query("device_id")
        device_id: String,
        @Query("user_id")
        user_id: String,
        @Query("catid")
        catid: String
    ): Response<SubCatAPIResponse>

    @GET("Products")
    suspend fun getProducts(
        @Query("device_id")
        device_id: String,
        @Query("user_id")
        user_id: String,
        @Query("subcatid")
        subcatid: String
    ): Response<ProductsAPIResponse>

    @FormUrlEncoded
    @POST("Login")
    suspend fun getUserDetails(
        @Field("device_id")
        device_id: String,
        @Field("phone")
        phone: String,
        @Field("password")
        password: String
    ): Response<UserDetailsAPIResponse>

    @FormUrlEncoded
    @POST("Register")
    suspend fun getRegister(
        @Field("device_id")
        device_id: String,
        @Field("name")
        name: String,
        @Field("email")
        email: String,
        @Field("phone")
        phone: String,
        @Field("password")
        password: String
    ): Response<UserDetailsAPIResponse>

    @FormUrlEncoded
    @POST("Addtocart")
    suspend fun getAddtocart(
        @Field("device_id")
        device_id: String,
        @Field("user_id")
        user_id: String,
        @Field("prod_id")
        prod_id: String,
        @Field("position")
        position: String,
        @Field("quantity")
        quantity: String
    ): Response<AddtocartAPIResponse>

}