package com.sonkins.bikeindex.presentation.router

import android.support.v4.app.FragmentManager
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import com.sonkins.bikeindex.presentation.util.FragmentController
import javax.inject.Inject

class Router @Inject constructor(
        private val fragmentController: FragmentController,
        private val fragmentManager: FragmentManager) {

    fun goToBikes() {
        fragmentController.setFragment(
                fragmentManager = fragmentManager,
                fragment = BikesFragment(),
                withBackStack = true,
                clearBackStack = true,
                saveState = false,
                reuse = true,
                existed = true
        )
    }

    fun goToSearch() {
        fragmentController.setFragment(
                fragmentManager = fragmentManager,
                fragment = SearchFragment(),
                withBackStack = true,
                clearBackStack = true,
                saveState = false,
                reuse = true,
                existed = true
        )
    }

    fun goToInfo() {
        fragmentController.setFragment(
                fragmentManager = fragmentManager,
                fragment = InfoFragment(),
                withBackStack = true,
                clearBackStack = true,
                saveState = false,
                reuse = true,
                existed = true
        )
    }

}