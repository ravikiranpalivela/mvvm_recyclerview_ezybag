package com.ravikiran.recyclerviewexample.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ravikiran.recyclerviewexample.data.repository.MainRepository
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val repository: MainRepository)  : ViewModel() {

    val mainList = MutableLiveData<MainAPIResponse>()
    val errorMessage = MutableLiveData<String>()

    val authList = MutableLiveData<UserDetailsAPIResponse>()
//    val navigationEvent: MutableLiveData<SingleEvent<NavController.() -> Any>> = MutableLiveData()


    fun getMainPage() {

        val response = repository.getMainPage("","")
        response.enqueue(object : Callback<MainAPIResponse> {
            override fun onResponse(call: Call<MainAPIResponse>, response: Response<MainAPIResponse>) {
                Log.d("taggy",response.body().toString())
                mainList.postValue(response.body())
            }

            override fun onFailure(call: Call<MainAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }

    fun getlogin(device_id: String,phone: String, password:String) {

        val response = repository.getLogin("",phone, password)
        response.enqueue(object : Callback<UserDetailsAPIResponse> {
            override fun onResponse(call: Call<UserDetailsAPIResponse>, response: Response<UserDetailsAPIResponse>) {
                Log.d("taggy",response.body().toString())
                authList.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailsAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }

    fun getRegister(device_id: String,name: String,email: String,phone: String, password:String) {

        val response = repository.getRegister("",name,phone,email, password)
        response.enqueue(object : Callback<UserDetailsAPIResponse> {
            override fun onResponse(call: Call<UserDetailsAPIResponse>, response: Response<UserDetailsAPIResponse>) {
                Log.d("taggy",response.body().toString())
                authList.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailsAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }

}


