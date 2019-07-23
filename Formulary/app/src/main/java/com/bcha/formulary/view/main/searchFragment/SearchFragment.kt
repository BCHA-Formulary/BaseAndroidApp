package com.bcha.formulary.view.main.searchFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.bcha.formulary.R
import com.bcha.formulary.view.base.BaseFragment

/**
 * Created by Kelvin Chan on 2018-11-04.
 */
class SearchFragment : BaseFragment(), SearchMVP.View {
    private lateinit var searchPresenter: SearchMVP.Presenter
    private lateinit var searchInteractor: SearchFragmentInteractor

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is SearchFragmentInteractor) {
            searchInteractor = context
        } else {
            throw RuntimeException("Activity does not implement SearchFragmentInteractor")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, view)
        setPresenter(SearchPresenter(this, SearchInteractor()))
        return view
    }

    override fun onStart() {
        super.onStart()
        searchPresenter.onStart()
    }

    override fun setPresenter(presenter: SearchMVP.Presenter) {
        this.searchPresenter = presenter
    }

    companion object {
        fun newInstance(): Fragment {
            val bundle = Bundle()
            val firstFragment = SearchFragment()
            firstFragment.arguments = bundle
            return firstFragment
        }
    }

    // To interact with the activity
    interface SearchFragmentInteractor : BaseFragmentInteractor
}