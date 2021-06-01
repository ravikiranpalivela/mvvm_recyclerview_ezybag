package com.ravikiran.recyclerviewexample.viewmodel
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravikiran.recyclerviewexample.data.repository.MainRepository
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.model.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val repository: MainRepository) : ViewModel() {

    val _loginResponse = MutableLiveData<UserDetailsAPIResponse>()
    val errorMessage = MutableLiveData<String>()

    fun login(device_id: String,phone: String, password: String) {

        val response = repository.getLogin(device_id ,phone,password)
        response.enqueue(object : Callback<UserDetailsAPIResponse> {
            override fun onResponse(call: Call<UserDetailsAPIResponse>, response: Response<UserDetailsAPIResponse>) {
                Log.d("taggy",response.body().toString())
                _loginResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailsAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }

    fun register(device_id: String,name: String,phone: String, email: String,password: String) {

        val response = repository.getRegister(device_id,name,phone,email,password)
        response.enqueue(object : Callback<UserDetailsAPIResponse> {
            override fun onResponse(call: Call<UserDetailsAPIResponse>, response: Response<UserDetailsAPIResponse>) {
                Log.d("taggy",response.body().toString())
                _loginResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailsAPIResponse>, t: Throwable) {
                Log.d("taggy","error"+t.toString())

                errorMessage.postValue(t.message)
            }
        })
    }

}