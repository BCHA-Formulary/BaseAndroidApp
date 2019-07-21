package com.bcha.formulary.view.base

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bcha.formulary.R

abstract class BaseActivity : AppCompatActivity(), BaseFragment.BaseFragmentInteractor {
    private val TAG = BaseActivity::class.java.simpleName
    protected var mCurrentVisibleFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_frame)
    }

    protected fun replaceFragment(activityContainerId: Int, fragment: Fragment, backstackTag: String? = null, args: Bundle? = null) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Optional: add fun animations
        args?.let { fragment.arguments = it }
        backstackTag?.let { fragmentTransaction.addToBackStack(it) }

        fragmentTransaction.replace(activityContainerId, fragment).commit()
        mCurrentVisibleFragment = fragment
    }

    override fun showToast(res: Int, duration: Int) {
        showToast(getString(res), duration)
    }

    override fun showToast(message: String, duration: Int) {
        uiChangeOnUiThread(Runnable { Toast.makeText(this@BaseActivity, message, duration).show() })
    }

    override fun uiChangeOnUiThread(uiRunnable: Runnable) {
        runOnUiThread(uiRunnable)
    }

    override fun hideSoftKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
