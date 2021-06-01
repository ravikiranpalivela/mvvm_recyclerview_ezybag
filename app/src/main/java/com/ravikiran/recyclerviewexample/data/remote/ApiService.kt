package com.ravikiran.recyclerviewexample.data.remote
import com.ravikiran.recyclerviewexample.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // getting data from api

    @FormUrlEncoded
    @POST("Login")
    fun login(
        @Field("device_id")
        device_id: String,
        @Field("phone")
        phone: String,
        @Field("password")
        password: String
    ): Call<UserDetailsAPIResponse>

    @FormUrlEncoded
    @POST("Register")
    fun register(
        @Field("device_id")
        device_id: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<UserDetailsAPIResponse>



    @GET("Mainpage")
    fun getMainPage(
        @Query("device_id")
        device_id: String,
        @Query("user_id")
        user_id: String
    ): Call<MainAPIResponse>
//    Response<MainAPIResponse>

    @GET("Subcategory")
    suspend fun getSubcategory(
        @Field("device_id")
        device_id: String,
        @Field("user_id")
        user_id: String,
        @Field("catid")
        catid: String
    ): Response<SubCatAPIResponse>

    @GET("Products")
    suspend fun getProducts(
        @Field("device_id")
        device_id: String,
        @Field("user_id")
        user_id: String,
        @Field("subcatid")
        subcatid: String
    ): Response<ProductsAPIResponse>

    @POST("Login")
    suspend fun getUserDetails(
        @Field("device_id")
        device_id: String,
        @Field("phone")
        phone: String,
        @Field("password")
        password: String
    ): Response<UserDetailsAPIResponse>

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

    companion object {

        var retrofitService: ApiService? = null

        fun getInstance() : ApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://ezybag.in/controladmin/api/Starsmartapi/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }

}