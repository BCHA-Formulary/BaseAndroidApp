package com.bcha.formulary.view.main

import com.bcha.formulary.view.base.BaseMVP

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class MainActivityMVP {
    interface Presenter : BaseMVP.BasePresenter

    interface View : BaseMVP.BaseView<Presenter>

    interface Interactor : BaseMVP.BaseInterator
}