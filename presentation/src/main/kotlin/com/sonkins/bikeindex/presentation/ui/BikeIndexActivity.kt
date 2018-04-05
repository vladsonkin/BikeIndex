package com.sonkins.bikeindex.presentation.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_bike_index.*
import javax.inject.Inject


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class BikeIndexActivity : BaseActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_index)

        if (savedInstanceState == null) {
            router.goToBikes()
        }

        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_bikes -> router.goToBikes()
            R.id.navigation_search -> router.goToSearch()
            R.id.navigation_info -> router.goToInfo()
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {

        // If we are on root fragments, finish application according to material guides
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment is BikesFragment ||
                currentFragment is SearchFragment ||
                currentFragment is InfoFragment) {
            finish()
        } else {
            super.onBackPressed()
        }

    }

}
