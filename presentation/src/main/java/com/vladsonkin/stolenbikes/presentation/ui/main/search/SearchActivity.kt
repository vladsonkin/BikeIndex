package com.vladsonkin.stolenbikes.presentation.ui.main.search

import android.os.Bundle
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation_single_fragment.*

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
open class SearchActivity : BaseNavigationActivity() {

    override fun getNavigationMenuItemId() = R.id.navigation_search

    override fun getContentViewId() = R.layout.activity_navigation_single_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_search)

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, SearchFragment())
        }
    }
}