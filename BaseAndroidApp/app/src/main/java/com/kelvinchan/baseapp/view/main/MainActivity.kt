package com.kelvinchan.baseapp.view.main

import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.kelvinchan.baseapp.R
import com.kelvinchan.baseapp.view.base.BaseActivity
import com.kelvinchan.baseapp.view.main.firstFragment.FirstFragment

/**
 * Created by Kelvin Chan on 2018-09-22.
 */
class MainActivity : BaseActivity(),
        MainActivityMVP.View,
        FirstFragment.FirstFragmentInteractorListener {
    private lateinit var mMainPresenter: MainActivityMVP.Presenter

    // View Bindings
    @BindView(R.id.frame_generic) lateinit var genericFrame: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_frame)
        ButterKnife.bind(this)

        setPresenter(MainPresenter(this, MainInteractor()))
        navigateToFirstFragment()
    }

    override fun onStart() {
        super.onStart()
        mMainPresenter.onStart()
    }

    override fun setPresenter(presenter: MainActivityMVP.Presenter) {
        this.mMainPresenter = presenter
    }

    private fun navigateToFirstFragment() {
        val firstFragment = FirstFragment.newInstance()
        replaceFragment(R.id.frame_generic, firstFragment)
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}