package com.ravikiran.recyclerviewexample.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravikiran.recyclerviewexample.data.usecase.*

class SharedViewModelFactory(
    private val getMainPageUseCase: GetMainPageUseCase,
    private val getLoginUseCase: GetLoginUseCase,
    private val getRegisterUseCase: GetRegisterUseCase,
    private val getSubCatPageUseCase: GetSubCatPageUseCase,
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(
            getMainPageUseCase,
            getLoginUseCase,
            getRegisterUseCase,
            getSubCatPageUseCase,
            app) as T
    }
}