package com.ravikiran.recyclerviewexample

import android.app.Application
import android.content.Context

class App : Application() {
    private var context: Context? = null

    private val mInstance: App? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun getContext(): Context? {
        return context
    }

    @Synchronized
    fun getInstance(): App? {
        return mInstance
    }

}