package com.kelvinchan.baseapp.view.main

import com.kelvinchan.baseapp.view.base.BaseMVP

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class MainActivityMVP {
    interface Presenter : BaseMVP.BasePresenter

    interface View : BaseMVP.BaseView<Presenter>

    interface Interactor : BaseMVP.BaseInterator
}