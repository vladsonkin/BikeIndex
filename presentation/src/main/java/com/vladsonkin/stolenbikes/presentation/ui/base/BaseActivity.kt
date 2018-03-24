package com.vladsonkin.stolenbikes.presentation.ui.base

import android.support.annotation.IdRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 *
 * Inherit from this activity if you don't want to use bottom bottom_navigation
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        this.supportFragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .commit()
    }

}