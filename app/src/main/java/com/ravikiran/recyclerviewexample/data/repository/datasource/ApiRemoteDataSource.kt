package com.ravikiran.recyclerviewexample.data.repository.datasource

import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import retrofit2.Response

interface ApiRemoteDataSource {

    suspend fun getMainPage(device_id: String, user_id: String): Response<MainAPIResponse>

    suspend fun getLogin(device_id: String, phone: String, password: String): Response<UserDetailsAPIResponse>

    suspend fun getRegister(device_id: String, name: String,phone: String, email: String, password: String): Response<UserDetailsAPIResponse>

    suspend fun getSubcat(device_id: String, user_id: String, catid: String): Response<SubCatAPIResponse>

    }