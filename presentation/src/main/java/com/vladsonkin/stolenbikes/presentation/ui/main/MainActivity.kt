package com.vladsonkin.stolenbikes.presentation.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.info.InfoFragment
import com.vladsonkin.stolenbikes.presentation.ui.main.search.SearchFragment
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(R.id.fragmentContainer, StolenBikesFragment())

        bottomNavigation.setOnNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_stolen_bikes -> replaceFragment(R.id.fragmentContainer, StolenBikesFragment())
            R.id.navigation_search -> replaceFragment(R.id.fragmentContainer, SearchFragment())
            R.id.navigation_info -> replaceFragment(R.id.fragmentContainer, InfoFragment())
        }

        return true
    }
}
