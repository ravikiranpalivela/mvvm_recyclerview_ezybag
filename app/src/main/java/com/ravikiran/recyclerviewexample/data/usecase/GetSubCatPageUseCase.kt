package com.ravikiran.recyclerviewexample.data.usecase

import com.ravikiran.recyclerviewexample.data.repository.ApiRepository
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import retrofit2.http.Query

class GetSubCatPageUseCase(private val repository: ApiRepository) {
    //calling  async task for get subcat
    suspend fun execute(device_id: String, user_id: String, catid: String): Resource<SubCatAPIResponse> {
        return repository.getSubCat(device_id, user_id, catid)
    }
}