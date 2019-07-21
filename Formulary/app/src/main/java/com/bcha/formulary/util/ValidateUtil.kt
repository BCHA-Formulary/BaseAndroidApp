package com.bcha.formulary.util

import android.text.TextUtils

class ValidateUtil {
    companion object {
        /**
         * Valid conditions are that the email be not empty and conforms to an email pattern
         * @param email
         * @return
         */
        fun isEmailValid(email: String): Boolean {
            return (!TextUtils.isEmpty(email) &&
                    android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        }
    }
}