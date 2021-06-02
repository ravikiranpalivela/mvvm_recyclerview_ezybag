package com.ravikiran.recyclerviewexample.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ravikiran.recyclerviewexample.data.usecase.GetLoginUseCase
import com.ravikiran.recyclerviewexample.data.usecase.GetMainPageUseCase
import com.ravikiran.recyclerviewexample.data.usecase.GetRegisterUseCase
import com.ravikiran.recyclerviewexample.data.usecase.GetSubCatPageUseCase
import com.ravikiran.recyclerviewexample.model.MainAPIResponse
import com.ravikiran.recyclerviewexample.model.SubCatAPIResponse
import com.ravikiran.recyclerviewexample.model.UserDetailsAPIResponse
import com.ravikiran.recyclerviewexample.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel @ViewModelInject constructor(
    private val getMainPageUseCase: GetMainPageUseCase,
    private val getLoginUseCase: GetLoginUseCase,
    private val getRegisterUseCase: GetRegisterUseCase,
    private val getSubCatPageUseCase: GetSubCatPageUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val mmainPage = MutableLiveData<Resource<MainAPIResponse>>()
    val mainPage: LiveData<Resource<MainAPIResponse>>
        get() = mmainPage

    private val msubcat = MutableLiveData<Resource<SubCatAPIResponse>>()
    val subcat: LiveData<Resource<SubCatAPIResponse>>
        get() = msubcat

    private val mLoginPage = MutableLiveData<Resource<UserDetailsAPIResponse>>()
    val loginpage: LiveData<Resource<UserDetailsAPIResponse>>
        get() = mLoginPage

    private val mRegisterPage = MutableLiveData<Resource<UserDetailsAPIResponse>>()
    val registerpage: LiveData<Resource<UserDetailsAPIResponse>>
        get() = mRegisterPage

    lateinit var name:String
    lateinit var email:String
    lateinit var phone:String
    lateinit var token:String
    lateinit var catid:String
    lateinit var subcatid:String


    fun getMainPage(device_id: String, user_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mmainPage.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    mmainPage.postValue(getMainPageUseCase.execute(device_id, user_id))
                } else {
                    mmainPage.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                Log.i("taggy", "EXCEPTION"+e.message)
                mmainPage.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }


    fun getSubcat(device_id: String, user_id: String, catid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            msubcat.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    msubcat.postValue(getSubCatPageUseCase.execute(device_id, user_id,catid))
                } else {
                    msubcat.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                Log.i("taggy", "EXCEPTION"+e.message)
                msubcat.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }


    fun getLogin(device_id: String, phone: String, password: String)  {
        viewModelScope.launch(Dispatchers.IO) {
            mLoginPage.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    mLoginPage.postValue(getLoginUseCase.execute(device_id, phone, password))
                } else {
                    mLoginPage.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                Log.i("taggy", "EXCEPTION"+e.message)
                mLoginPage.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }

    fun getRegister(device_id: String, name: String,phone: String, email: String, password: String)  {
        viewModelScope.launch(Dispatchers.IO) {
            mRegisterPage.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    mRegisterPage.postValue(getRegisterUseCase.execute(device_id, name,phone, email, password))
                } else {
                    mRegisterPage.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                Log.i("taggy", "EXCEPTION"+e.message)
                mRegisterPage.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}