package com.sonkins.bikeindex.presentation.router

import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragment
import com.sonkins.bikeindex.presentation.util.FragmentController
import javax.inject.Inject

class Router @Inject constructor(
        private val fragmentController: FragmentController,
        private val context: BaseActivity) {

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

    fun startFilterActivity() {
        FilterActivity.start(context)
    }

    fun showFilterFragment() {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = FilterFragment(),
                withBackStack = true,
                saveState = false
        )
    }

}