package com.sonkins.bikeindex.presentation.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragment
import com.sonkins.bikeindex.presentation.ui.main.bikeindex.StolenBikesFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addOrShowExistingFragment(R.id.fragmentContainer, StolenBikesFragment())

        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_stolen_bikes -> addOrShowExistingFragment(R.id.fragmentContainer, StolenBikesFragment())
            R.id.navigation_search -> addOrShowExistingFragment(R.id.fragmentContainer, SearchFragment())
            R.id.navigation_info -> addOrShowExistingFragment(R.id.fragmentContainer, InfoFragment())
        }

        return true
    }

    // TODO handle back from fragments?
    override fun onBackPressed() {
        finish()
    }
}
