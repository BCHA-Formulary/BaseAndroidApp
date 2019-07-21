package com.bcha.formulary.util

import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.bcha.formulary.FormularyApplication

class ResourceManager {
    companion object {
        fun getResources(): Resources {
            return FormularyApplication.mAppContext.resources
        }

        fun getColorFromRes(colorRes: Int): Int {
            return ContextCompat.getColor(FormularyApplication.mAppContext, colorRes)
        }

        fun getStringFromRes(stringRes: Int): String {
            return FormularyApplication.mAppContext.getString(stringRes)
        }
    }
}