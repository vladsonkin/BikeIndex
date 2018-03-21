package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import android.os.Bundle
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation_single_fragment.*


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class StolenBikesActivity : BaseNavigationActivity() {

    override fun getNavigationMenuItemId() = R.id.navigation_stolen_bikes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_stolen_bikes)

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, StolenBikesFragment())
        }
    }
}
