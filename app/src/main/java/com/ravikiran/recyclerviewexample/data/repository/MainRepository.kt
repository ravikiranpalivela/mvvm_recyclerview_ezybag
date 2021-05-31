package com.ravikiran.recyclerviewexample.data.repository

import com.ravikiran.recyclerviewexample.data.remote.ApiService
import retrofit2.http.Field

class MainRepository constructor(private val apiService: ApiService) {

    fun getMainPage(device_id: String, user_id: String) = apiService.getMainPage(device_id, user_id)
}