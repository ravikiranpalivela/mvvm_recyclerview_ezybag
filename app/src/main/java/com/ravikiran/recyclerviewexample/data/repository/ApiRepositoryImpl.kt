package com.ravikiran.recyclerviewexample.data.repository

import android.util.Log
import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.ProductsAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ApiRepositoryImpl(
    private val newsRemoteDataSource: ApiRemoteDataSource,
) : ApiRepository {

    private fun responseToMainResource(response: Response<MainAPIResponse>): Resource<MainAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d("taggy", "Main get Response Success" + it.toString())
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }

    private fun responseToSubCatResource(response: Response<SubCatAPIResponse>): Resource<SubCatAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d("taggy", "subcat get Response Success" + it.toString())
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }

    private fun responseToProductResource(response: Response<ProductsAPIResponse>): Resource<ProductsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d("taggy", "product get Response Success" + it.toString())
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }


    private fun responseToAuthResource(response: Response<UserDetailsAPIResponse>): Resource<UserDetailsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d("taggy", "Auth get Response Success" + it.toString())
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

    override suspend fun getSubCat(device_id: String, user_id: String, catid: String): Resource<SubCatAPIResponse> {
        return responseToSubCatResource(newsRemoteDataSource.getSubcat(device_id, user_id, catid))
    }

    override suspend fun getProduct(device_id: String, user_id: String, subcatid: String): Resource<ProductsAPIResponse> {
        return responseToProductResource(newsRemoteDataSource.getProduct(device_id, user_id, subcatid))

    }

}