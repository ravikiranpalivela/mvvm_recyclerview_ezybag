package com.ravikiran.recyclerviewexample.data.repository

import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiRepository {

    suspend fun getMainPage(device_id: String, user_id: String): Resource<MainAPIResponse>

    suspend fun getLogin(device_id: String, phone: String, password: String): Resource<UserDetailsAPIResponse>

    suspend fun getRegister(device_id: String, name: String,phone: String, email: String, password: String): Resource<UserDetailsAPIResponse>

}