package com.bcha.formulary.view.main

import com.bcha.formulary.model.FormularyStatus
import com.bcha.formulary.repository.EndpointUtil
import com.bcha.formulary.util.FirebaseUtil
import com.bcha.formulary.util.SharedPrefManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber
import java.lang.Exception

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class MainPresenter(val mainView: MainActivityMVP.View,
                    val mainInteractor: MainActivityMVP.Interactor)
    : MainActivityMVP.Presenter {

    private val database = FirebaseDatabase.getInstance().reference
    private val lastUpdatedListener = object : ValueEventListener {
        override fun onCancelled(err: DatabaseError) {
            Timber.e(err.toException())
            mainView.updateStatus(FormularyStatus.OFFLINE)
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            val lastUpdated = snapshot.getValue(String::class.java)
            Timber.d("Last updated: $lastUpdated")
            lastUpdated?.let { checkFormularyUpdate(it) } ?:run { mainView.updateStatus(FormularyStatus.OFFLINE) }
        }
    }

    override fun onStart() {
        database.child(EndpointUtil.Firebase.LAST_UPDATED).addValueEventListener(lastUpdatedListener)
    }

    override fun onStop() {
        database.child(EndpointUtil.Firebase.LAST_UPDATED).removeEventListener(lastUpdatedListener)
    }

    override fun onDestroy() {}

    private fun checkFormularyUpdate(latestFirebaseUpdate: String) {
        SharedPrefManager.sharedPrefInstance?.let {
            val lastSuccessfulUpdate = it.getString(
                    EndpointUtil.SharedPref.LAST_UPDATE_KEY, "")
            if (lastSuccessfulUpdate != latestFirebaseUpdate) {
                updateFormulary()
            } else {
                mainView.updateStatus(FormularyStatus.UP_TO_DATE)
            }
        }?:run {
            mainView.updateStatus(FormularyStatus.OFFLINE)
        }
    }

    private fun updateFormulary() {
        FirebaseUtil.updateFormulary(object: FirebaseUtil.UpdateCompletion {
            override fun onComplete(lastSynced: String) {
                SharedPrefManager.sharedPrefInstance?.putString(
                        EndpointUtil.SharedPref.LAST_UPDATE_KEY, lastSynced)
                mainView.updateStatus(FormularyStatus.UP_TO_DATE)
            }

            override fun onError(e: Exception?) {
                e?.let { Timber.e(e) }
                mainView.updateStatus(FormularyStatus.OFFLINE)
            }
        })
    }
}