package com.bcha.formulary.view.base

/**
 * Created by Kelvin Chan on 2018-09-23.
 */
interface BaseMVP {
    interface BasePresenter {
        fun onStart()
        fun onStop()
        fun onDestroy()
    }
    interface BaseView<T> {
        fun setPresenter(presenter: T)
    }
    interface BaseInterator {
        fun unsubscribe()
        fun onDestroy()
    }
}