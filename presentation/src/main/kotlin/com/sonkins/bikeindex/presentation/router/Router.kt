package com.sonkins.bikeindex.presentation.router

import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragment
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragment
import com.sonkins.bikeindex.presentation.util.FragmentController
import javax.inject.Inject

class Router @Inject constructor(
        private val fragmentController: FragmentController,
        private val context: BaseActivity) {

    // Switch bottom navigation tabs

    fun switchBikesTab(reuse: Boolean = false) {
        fragmentController.switchTab(
                fragmentManager = context.supportFragmentManager,
                fragment = BikesFragment(),
                reuse = reuse
        )
    }

    fun switchSearchTab(reuse: Boolean = false) {
        fragmentController.switchTab(
                fragmentManager = context.supportFragmentManager,
                fragment = SearchFragment(),
                reuse = reuse
        )
    }

    fun switchInfoTab(reuse: Boolean = false) {
        fragmentController.switchTab(
                fragmentManager = context.supportFragmentManager,
                fragment = InfoFragment(),
                reuse = reuse
        )
    }

    // Start activity

    fun startFilterActivity(fragment: Fragment, requestCode: Int, filter: Filter) {
        FilterActivity.startForResult(fragment, requestCode, filter)
    }

    // Show fragments

    fun showFilterFragment() {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = FilterFragment(),
                withBackStack = true,
                saveState = false
        )
    }

    fun showManufacturersFragment() {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = ManufacturersFragment(),
                withBackStack = true,
                saveState = false
        )
    }

    fun showColorsFragment() {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = ColorsFragment(),
                withBackStack = true,
                saveState = false
        )
    }

}