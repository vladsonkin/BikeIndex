package com.sonkins.bikeindex.presentation.ui.base

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    // TODO use FragNav library ?
    protected fun addOrShowExistingFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        val fragmentTag = fragment.javaClass.simpleName

        val fragmentPopped = this.supportFragmentManager.popBackStackImmediate(fragmentTag, 0)

        if (!fragmentPopped) {
            this.supportFragmentManager.beginTransaction()
                    .add(containerViewId, fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit()
        }

    }

}