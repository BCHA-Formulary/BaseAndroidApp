package com.bcha.formulary.repository

class EndpointUtil {
    companion object {
        val TAG: String = EndpointUtil::class.java.simpleName
    }

    class Firebase {
        companion object {
            const val LAST_UPDATED = "Update"
        }
    }

    class SharedPref {
        companion object {
            const val LAST_UPDATE_KEY = "lastUpdated"
        }
    }
}