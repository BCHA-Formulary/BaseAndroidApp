package com.bcha.formulary.util

import java.lang.Exception

class FirebaseUtil {
    companion object {
        fun updateFormulary(completion: UpdateCompletion) {

        }
    }

    interface UpdateCompletion {
        fun onComplete(lastSynced: String)
        fun onError(e: Exception?)
    }
}