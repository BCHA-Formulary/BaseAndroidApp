package com.bcha.formulary.view.splash

import android.content.Intent
import android.os.Bundle
import com.bcha.formulary.view.base.BaseActivity
import com.bcha.formulary.view.main.MainActivity

/**
 * Created by Kelvin Chan on 2019-07-20.
 */
class SplashActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}