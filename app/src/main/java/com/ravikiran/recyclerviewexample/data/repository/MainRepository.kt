package com.ravikiran.recyclerviewexample.data.repository

import com.ravikiran.recyclerviewexample.data.remote.ApiService
import retrofit2.http.Field

class MainRepository constructor(private val apiService: ApiService) {

    fun getMainPage(device_id: String, user_id: String) = apiService.getMainPage(device_id, user_id)

    fun getLogin(device_id: String, phone: String, password: String) = apiService.login(device_id, phone,password)

    fun getRegister(device_id: String, name: String,phone: String, email: String, password: String) = apiService.register(device_id,name, phone,email,password)

}