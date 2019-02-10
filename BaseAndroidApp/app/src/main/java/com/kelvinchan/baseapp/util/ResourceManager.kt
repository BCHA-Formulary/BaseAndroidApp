package com.kelvinchan.baseapp.util

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import com.kelvinchan.baseapp.BaseApplication

class ResourceManager {
    companion object {
        fun getResources(): Resources {
            return BaseApplication.mAppContext.resources
        }

        fun getColorFromRes(colorRes: Int): Int {
            return ContextCompat.getColor(BaseApplication.mAppContext, colorRes)
        }

        fun getStringFromRes(stringRes: Int): String {
            return BaseApplication.mAppContext.getString(stringRes)
        }
    }
}