package com.sonkins.bikeindex.presentation.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_bike_index.*


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class BikeIndexActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_index)
        addOrShowExistingFragment(R.id.fragmentContainer, BikesFragment())

        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_stolen_bikes -> addOrShowExistingFragment(R.id.fragmentContainer, BikesFragment())
            R.id.navigation_search -> addOrShowExistingFragment(R.id.fragmentContainer, SearchFragment())
            R.id.navigation_info -> addOrShowExistingFragment(R.id.fragmentContainer, InfoFragment())
        }

        return true
    }

}
