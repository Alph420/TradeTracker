package com.alphacorporations.tradetracker

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseActivity : Application() {
    private var sApplication: Application? = null

    fun getApplication(): Application {
        return sApplication!!
    }

    fun getContext(): Context? {
        return getApplication().applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }
}