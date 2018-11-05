package com.kelvinchan.baseapp.view.main.firstFragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.kelvinchan.baseapp.R
import com.kelvinchan.baseapp.view.base.BaseFragment

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class FirstFragment: BaseFragment(), FirstMVP.View {
    lateinit var mFirstPresenter: FirstMVP.Presenter
    lateinit var mFirstInteractorListener: FirstFragmentInteractorListener

    @BindView (R.id.firstText)
    lateinit var firstText: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is FirstFragmentInteractorListener) {
            mFirstInteractorListener = context
        } else {
            throw RuntimeException("Activity does not implement FirstFragmentInteractorListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        ButterKnife.bind(this, view)
        setPresenter(FirstPresenter(this, FirstInteractor()))
        return view
    }

    override fun onStart() {
        super.onStart()
        mFirstPresenter.onStart()
    }

    override fun setPresenter(presenter: FirstMVP.Presenter) {
        this.mFirstPresenter = presenter
    }

    companion object {
        fun newInstance(): Fragment {
            val bundle = Bundle()
            val firstFragment = FirstFragment()
            firstFragment.setArguments(bundle)
            return firstFragment
        }
    }

    // To interact with the activity
    interface FirstFragmentInteractorListener: BaseFragmentInteractorListener
}