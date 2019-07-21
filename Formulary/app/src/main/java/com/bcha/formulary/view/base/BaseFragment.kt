package com.bcha.formulary.view.base

import android.content.Context
import androidx.fragment.app.Fragment
import android.view.View
import java.lang.ref.WeakReference

/**
 * Created by Kelvin Chan on 2018-09-23.
 */
abstract class BaseFragment : Fragment() {
    protected val TAG = BaseFragment::class.java.simpleName
    protected var mContext: WeakReference<Context>? = null
    private var mFragmentInteractor: BaseFragmentInteractorListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            mContext = WeakReference(it)
            if (it is BaseFragmentInteractorListener) {
                mFragmentInteractor = it
            } else {
                throw RuntimeException("Activity does not implement BaseFragmentInteractorListener")
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (mFragmentInteractor == null) {
            if (mContext is BaseFragmentInteractorListener) {
                mFragmentInteractor = mContext as BaseFragmentInteractorListener
            } else {
                throw RuntimeException("Activity does not implement BaseFragmentInteractorListener")
            }
        }
    }

    // ======== Toast ========
    protected fun showToast(messageRes: Int, duration: Int) {
        mFragmentInteractor?.showToast(messageRes, duration)
    }

    protected fun showToast(message: String, duration: Int) {
        mFragmentInteractor?.showToast(message, duration)
    }

    // ====== Threading =======
    protected fun runUpdateOnUiThread(uiRunnable: Runnable) {
        mFragmentInteractor?.uiChangeOnUiThread(uiRunnable)
    }

    // ====== Interaction =====
    protected fun hideSoftKeyboard(view: View) {
        mFragmentInteractor?.hideSoftKeyboard(view)
    }

    interface BaseFragmentInteractorListener {
        fun showToast(res: Int, duration: Int)
        fun showToast(message: String, duration: Int)
        fun uiChangeOnUiThread(uiRunnable: Runnable)
        fun hideSoftKeyboard(view: View)
    }
}
