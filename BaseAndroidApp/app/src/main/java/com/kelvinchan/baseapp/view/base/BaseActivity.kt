package com.kelvinchan.baseapp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kelvinchan.baseapp.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
