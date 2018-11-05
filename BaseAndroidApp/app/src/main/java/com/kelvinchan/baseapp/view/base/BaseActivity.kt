package com.kelvinchan.baseapp.view.base

import android.accounts.NetworkErrorException
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kelvinchan.baseapp.R
import io.reactivex.CompletableObserver

abstract class BaseActivity : AppCompatActivity(), BaseFragment.BaseFragmentInteractorListener {
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

    /**
     * Opens a url in the default browser
     * @param uri
     * @param webViewCompletable - complete if open success/error if user has no browser or url is null
     */
    override fun openUriInBrowser(uri: Uri?, webViewCompletable: CompletableObserver) {
        // check if url is null (malformed can cause null)
        if (uri == null) {
            webViewCompletable.onError(NetworkErrorException("Uri null"))
            return
        }

        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        // will be null if the phone has no browser able to open the url
        if (browserIntent.resolveActivity(packageManager) != null) {
            startActivity(browserIntent)
            webViewCompletable.onComplete()
        } else {
            webViewCompletable.onError(NetworkErrorException("No browser found to handle uri :" + uri.toString()))
        }
    }
}
