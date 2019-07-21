package com.bcha.formulary

import android.app.Application
import android.content.Context

class FormularyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mAppContext = this

        /* Optional to init the shared pref here
        SharedPrefManager.initialize(this)
         */
    }

    companion object {
        val TAG: String = FormularyApplication::class.java.simpleName
        lateinit var mAppContext: Context
    }
}