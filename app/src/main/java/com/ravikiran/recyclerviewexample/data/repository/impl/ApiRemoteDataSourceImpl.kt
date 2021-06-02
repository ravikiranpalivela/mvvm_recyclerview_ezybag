package com.ravikiran.recyclerviewexample.data.repository.impl

import com.ravikiran.recyclerviewexample.data.remote.ApiService
import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import retrofit2.Response

class ApiRemoteDataSourceImpl(private val apiService: ApiService) : ApiRemoteDataSource {

    // getting top news from rest api
    override suspend fun getMainPage(device_id: String, user_id: String): Response<MainAPIResponse> {
        return apiService.getMainPageData(device_id, user_id)
    }

    override suspend fun getLogin(device_id: String, phone: String, password: String): Response<UserDetailsAPIResponse> {
        return apiService.getUserDetails(device_id, phone, password)
    }

    override suspend fun getRegister(device_id: String, name: String, phone: String, email: String, password: String): Response<UserDetailsAPIResponse> {
        return apiService.getRegister(device_id, name, phone, email, password)
    }

    override suspend fun getSubcat(device_id: String, user_id: String, catid: String): Response<SubCatAPIResponse> {
        return apiService.getSubcategory(device_id, user_id, catid)
    }


}