package com.vladsonkin.stolenbikes.presentation.ui.main.info

import android.os.Bundle
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation_single_fragment.*

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
open class InfoActivity : BaseNavigationActivity() {

    override fun getNavigationMenuItemId() = R.id.navigation_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_info)

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, InfoFragment())
        }
    }
}