package com.sonkins.bikeindex.presentation.ui.base

import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    // TODO handle back from fragments?
    override fun onBackPressed() {
        finish()
    }
}