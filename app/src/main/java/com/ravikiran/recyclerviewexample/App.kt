package com.ravikiran.recyclerviewexample

import android.app.Application
import android.content.Context
import com.ravikiran.recyclerviewexample.data.local.SharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private var context: Context? = null

    private val mInstance: App? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        SharedPref.init(this)

    }

    fun getContext(): Context? {
        return context
    }

    @Synchronized
    fun getInstance(): App? {
        return mInstance
    }

}