package com.sonkins.bikeindex.presentation.router

import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragment
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.presentation.ui.filter.type.TypeFragment
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

    fun startFilterActivity(fragment: Fragment, requestCode: Int, filterModel: FilterModel) {
        FilterActivity.startForResult(fragment, requestCode, filterModel)
    }

    // Show fragments

    fun showFilterFragment(filterModel: FilterModel) {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = FilterFragment.newInstance(filterModel),
                withBackStack = true,
                saveState = false
        )
    }

    fun showManufacturersFragment(targetFragment: Fragment, requestCode: Int, filterModel: FilterModel) {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = ManufacturersFragment.newInstance(filterModel),
                withBackStack = true,
                saveState = false,
                targetFragment = targetFragment,
                requestCode = requestCode
        )
    }

    fun showColorsFragment(targetFragment: Fragment, requestCode: Int, filterModel: FilterModel) {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = ColorsFragment.newInstance(filterModel),
                withBackStack = true,
                saveState = false,
                targetFragment = targetFragment,
                requestCode = requestCode
        )
    }

    fun showTypeFragment(targetFragment: Fragment, requestCode: Int, filterModel: FilterModel) {
        fragmentController.setFragment(
                fragmentManager = context.supportFragmentManager,
                fragment = TypeFragment.newInstance(filterModel),
                withBackStack = true,
                saveState = false,
                targetFragment = targetFragment,
                requestCode = requestCode
        )
    }

}