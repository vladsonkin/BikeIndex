package com.sonkins.bikeindex.presentation.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragment
import kotlinx.android.synthetic.main.activity_single_fragment_nav.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class MainActivity : BaseActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{

    @Inject lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment_nav)

        if (savedInstanceState == null) {
            router.switchBikesTab()
        }

        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.setOnNavigationItemReselectedListener(this)

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            when (currentFragment) {
                is BikesFragment -> bottomNavigation.menu.getItem(0).isChecked = true
                is SearchFragment -> bottomNavigation.menu.getItem(1).isChecked = true
                is InfoFragment -> bottomNavigation.menu.getItem(2).isChecked = true
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_bikes -> router.switchBikesTab()
            R.id.navigation_search -> router.switchSearchTab()
            R.id.navigation_info -> router.switchInfoTab()
        }

        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId) {
            R.id.navigation_bikes -> router.switchBikesTab(true)
            R.id.navigation_search -> router.switchSearchTab(true)
            R.id.navigation_info -> router.switchInfoTab(true)
        }
    }

}
