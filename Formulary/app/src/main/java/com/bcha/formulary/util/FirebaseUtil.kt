package com.bcha.formulary.util

import android.os.AsyncTask
import androidx.room.Room
import com.bcha.formulary.FormularyApplication
import com.bcha.formulary.model.DATABASE_NAME
import com.bcha.formulary.model.DrugDatabase
import com.bcha.formulary.model.SqlDrug
import timber.log.Timber

class FirebaseUtil {
    companion object {
        fun updateFormulary(completion: UpdateCompletion) {
            val db = Room.databaseBuilder(FormularyApplication.mAppContext, DrugDatabase::class.java,DATABASE_NAME).build()
            val drug = SqlDrug("drugA", "drugType", "drugStatus", "drugClass")
            AsyncTask.execute {
                db.drugDao().insertDrug(drug)
                val names = db.drugDao().getAllDrugNames()
                Timber.d("Entries: ${names.size} Names: ${names[0]}")
            }
        }
    }

    interface UpdateCompletion {
        fun onComplete(lastSynced: String)
        fun onError(e: Exception?)
    }
}