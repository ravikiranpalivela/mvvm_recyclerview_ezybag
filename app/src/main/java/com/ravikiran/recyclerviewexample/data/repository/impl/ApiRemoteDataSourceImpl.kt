package com.ravikiran.recyclerviewexample.data.repository.impl

import android.util.Log
import com.ravikiran.recyclerviewexample.data.remote.ApiService
import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.ProductsAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import retrofit2.Response

class ApiRemoteDataSourceImpl(private val apiService: ApiService) : ApiRemoteDataSource {

    // getting top news from rest api
    override suspend fun getMainPage(device_id: String, user_id: String): Response<MainAPIResponse> {
        Log.d("taggy","Main page get values device_id: $device_id, user_id: $user_id")
        return apiService.getMainPageData(device_id, user_id)
    }

    override suspend fun getLogin(device_id: String, phone: String, password: String): Response<UserDetailsAPIResponse> {
        Log.d("taggy","Login post values device_id: $device_id, phone: $phone, password: $password")
        return apiService.getUserDetails(device_id, phone, password)
    }

    override suspend fun getRegister(device_id: String, name: String, phone: String, email: String, password: String): Response<UserDetailsAPIResponse> {
        Log.d("taggy","register post values device_id: $device_id,name: $name, phone: $phone, email: $email, password: $password")
        return apiService.getRegister(device_id, name, phone, email, password)
    }

    override suspend fun getSubcat(device_id: String, user_id: String, catid: String): Response<SubCatAPIResponse> {
        Log.d("taggy","subcat get values device_id: $device_id, user_id: $user_id, catid: $catid")
        return apiService.getSubcategory(device_id, user_id, catid)
    }

    override suspend fun getProduct(device_id: String, user_id: String, subcatid: String): Response<ProductsAPIResponse> {
        Log.d("taggy","product get values device_id: $device_id, user_id: $user_id, subcatid: $subcatid")
        return apiService.getProducts(device_id, user_id, subcatid)
    }


}