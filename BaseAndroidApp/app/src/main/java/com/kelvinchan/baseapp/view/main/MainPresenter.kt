package com.kelvinchan.baseapp.view.main

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class MainPresenter (val mainView: MainActivityMVP.View, val mainInteractor: MainActivityMVP.Interactor)
    : MainActivityMVP.Presenter {
    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

}