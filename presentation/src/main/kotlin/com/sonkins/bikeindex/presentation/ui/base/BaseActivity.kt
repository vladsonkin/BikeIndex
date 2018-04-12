package com.sonkins.bikeindex.presentation.ui.base

import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            Timber.i("backStack: " + supportFragmentManager.backStackEntryCount)
            super.onBackPressed()
        }
    }

}