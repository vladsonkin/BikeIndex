package com.vladsonkin.stolenbikes.presentation.ui.base

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        this.supportFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit()
    }

}