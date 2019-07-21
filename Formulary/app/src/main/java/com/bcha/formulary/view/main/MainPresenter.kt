package com.bcha.formulary.view.main

import com.bcha.formulary.repository.network.EndpointUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class MainPresenter(val mainView: MainActivityMVP.View, val mainInteractor: MainActivityMVP.Interactor)
    : MainActivityMVP.Presenter {

    private val database = FirebaseDatabase.getInstance().reference

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {}

    override fun checkFormularyUpdate() {
        database.child(EndpointUtil.Firebase.LAST_UPDATED).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(err: DatabaseError) {
                Timber.e(err.toException())
                //TODO make offline
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val lastUpdated = snapshot.getValue(String::class.java)
                Timber.d("Last updated: $lastUpdated")
            }
        })
    }
}