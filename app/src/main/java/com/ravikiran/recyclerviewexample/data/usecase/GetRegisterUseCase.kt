package com.ravikiran.recyclerviewexample.data.usecase

import com.ravikiran.recyclerviewexample.data.repository.ApiRepository
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource

class GetRegisterUseCase(private val repository: ApiRepository) {
    //calling  async task for get main page
    suspend fun execute(device_id: String, name: String,phone: String, email: String, password: String) : Resource<UserDetailsAPIResponse> {
        return repository.getRegister(device_id, name,phone, email, password)
    }
}