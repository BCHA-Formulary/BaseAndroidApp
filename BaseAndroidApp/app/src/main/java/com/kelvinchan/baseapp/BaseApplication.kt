package com.kelvinchan.baseapp

import android.app.Application
import android.content.Context

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        mAppContext = this
    }

    companion object {
        val TAG: String = BaseApplication::class.java.simpleName
        lateinit var mAppContext: Context
    }
}