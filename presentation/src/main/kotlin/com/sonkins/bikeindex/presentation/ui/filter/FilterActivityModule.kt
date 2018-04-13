package com.sonkins.bikeindex.presentation.ui.filter

import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragment
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragmentModule
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 11 April 2018.
 */
@Module
interface FilterActivityModule {

    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    fun filterFragmentInjector(): FilterFragment

    @ContributesAndroidInjector(modules = [ManufacturersFragmentModule::class])
    fun manufacturersFragmentInjector(): ManufacturersFragment

    @ContributesAndroidInjector(modules = [ColorsFragmentModule::class])
    fun colorsFragmentInjector(): ColorsFragment

    @Binds
    fun bindContext(context: FilterActivity): BaseActivity

}