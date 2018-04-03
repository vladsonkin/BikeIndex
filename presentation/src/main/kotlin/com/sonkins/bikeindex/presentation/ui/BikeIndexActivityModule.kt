package com.sonkins.bikeindex.presentation.ui

import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragmentModule
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragmentModule
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragmentModule
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 03 April 2018.
 */
@Module
interface BikeIndexActivityModule {

    @ContributesAndroidInjector(modules = [BikesFragmentModule::class])
    fun provideBikesFragment(): BikesFragment

    @ContributesAndroidInjector(modules = [InfoFragmentModule::class])
    fun provideInfoFragment(): InfoFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    fun provideFilterFragment(): FilterFragment

}