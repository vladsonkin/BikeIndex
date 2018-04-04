package com.sonkins.bikeindex.presentation.router

import android.support.v4.app.FragmentManager
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import com.sonkins.bikeindex.presentation.util.FragmentController
import java.util.logging.Filter
import javax.inject.Inject

class Router @Inject constructor(
        private val fragmentController: FragmentController,
        private val fragmentManager: FragmentManager) {

    fun goToBikes() {
        fragmentController.switchTab(
                fragmentManager = fragmentManager,
                fragment = BikesFragment()
        )
    }

    fun goToSearch() {
        fragmentController.switchTab(
                fragmentManager = fragmentManager,
                fragment = SearchFragment()
        )
    }

    fun goToInfo() {
        fragmentController.switchTab(
                fragmentManager = fragmentManager,
                fragment = InfoFragment()
        )
    }

    fun goToFilter() {
        fragmentController.setFragment(
                fragmentManager = fragmentManager,
                fragment = FilterFragment()
        )
    }

}