package com.ravikiran.recyclerviewexample.data.usecase

import com.ravikiran.recyclerviewexample.data.repository.ApiRepository
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource

class GetMainPageUseCase(private val repository: ApiRepository) {
    //calling  async task for get main page
    suspend fun execute(device_id: String, user_id: String) : Resource<MainAPIResponse> {
        return repository.getMainPage(device_id, user_id)
    }
}