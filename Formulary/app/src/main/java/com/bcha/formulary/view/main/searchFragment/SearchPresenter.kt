package com.bcha.formulary.view.main.searchFragment

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class SearchPresenter(val searchView: SearchMVP.View, val searchInteractor: SearchMVP.Interactor) : SearchMVP.Presenter {
    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    companion object {
        val TAG: String = SearchPresenter::class.java.simpleName
    }
}