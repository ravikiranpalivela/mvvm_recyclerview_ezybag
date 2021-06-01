package com.ravikiran.recyclerviewexample.data.repository

import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ApiRepositoryImpl(
    private val newsRemoteDataSource: ApiRemoteDataSource,
) : ApiRepository {

    private fun responseToMainResource(response: Response<MainAPIResponse>):Resource<MainAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }

    private fun responseToAuthResource(response: Response<UserDetailsAPIResponse>):Resource<UserDetailsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }

    override suspend fun getMainPage(device_id: String, user_id: String): Resource<MainAPIResponse> {
        return responseToMainResource(newsRemoteDataSource.getMainPage(device_id, user_id))
    }

    override suspend fun getLogin(device_id: String, phone: String, password: String): Resource<UserDetailsAPIResponse> {
        return responseToAuthResource(newsRemoteDataSource.getLogin(device_id, phone, password))
    }

    override suspend fun getRegister(device_id: String, name: String, phone: String, email: String, password: String): Resource<UserDetailsAPIResponse> {
        return responseToAuthResource(newsRemoteDataSource.getRegister(device_id, name, phone, email, password))
    }
}