package com.sonkins.bikeindex.presentation.ui.filter

import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragment
import com.sonkins.bikeindex.presentation.ui.filter.colors.ColorsFragmentModule
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.presentation.ui.filter.manufacturers.ManufacturersFragmentModule
import com.sonkins.bikeindex.presentation.ui.filter.type.TypeFragment
import com.sonkins.bikeindex.presentation.ui.filter.type.TypeFragmentModule
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

    @ContributesAndroidInjector(modules = [TypeFragmentModule::class])
    fun typeFragmentInjector(): TypeFragment

    @Binds
    fun bindContext(context: FilterActivity): BaseActivity

}