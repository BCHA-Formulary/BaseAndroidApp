package com.bcha.formulary

import android.app.Application
import android.content.Context
import com.bcha.formulary.util.SharedPrefManager
import timber.log.Timber

class FormularyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mAppContext = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        SharedPrefManager.initialize(this)
    }

    companion object {
        val TAG: String = FormularyApplication::class.java.simpleName
        lateinit var mAppContext: Context
    }
}