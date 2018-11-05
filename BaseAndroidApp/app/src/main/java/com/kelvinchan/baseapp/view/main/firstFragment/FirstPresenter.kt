package com.kelvinchan.baseapp.view.main.firstFragment

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class FirstPresenter(val firstView: FirstMVP.View, val firstInteractor: FirstMVP.Interactor): FirstMVP.Presenter {
    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    companion object {
        val TAG: String = FirstPresenter::class.java.simpleName
    }
}