package com.ravikiran.recyclerviewexample.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravikiran.recyclerviewexample.data.repository.MainRepository
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val movieList = MutableLiveData<MainAPIResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {

        val response = repository.getMainPage("","")
        response.enqueue(object : Callback<MainAPIResponse> {
            override fun onResponse(call: Call<MainAPIResponse>, response: Response<MainAPIResponse>) {
                Log.d("taggy",response.body().toString())
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MainAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }
}


