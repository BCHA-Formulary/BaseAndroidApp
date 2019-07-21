package com.bcha.formulary.view.main.firstFragment

import com.bcha.formulary.view.base.BaseMVP

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class FirstMVP {
    interface Presenter : BaseMVP.BasePresenter
    interface View : BaseMVP.BaseView<Presenter>
    interface Interactor : BaseMVP.BaseInterator
}