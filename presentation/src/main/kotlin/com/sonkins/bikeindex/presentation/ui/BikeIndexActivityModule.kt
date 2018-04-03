package com.sonkins.bikeindex.presentation.ui

import android.support.v4.app.FragmentManager
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragmentModule
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.bikes.BikesFragmentModule
import com.sonkins.bikeindex.presentation.ui.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.info.InfoFragmentModule
import com.sonkins.bikeindex.presentation.ui.search.SearchFragment
import com.sonkins.bikeindex.presentation.ui.search.SearchFragmentModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 03 April 2018.
 */
@Module(includes = [ProvidesBikeIndexActivityModule::class])
interface BikeIndexActivityModule {

    @ContributesAndroidInjector(modules = [BikesFragmentModule::class])
    fun bikesFragmentInjector(): BikesFragment

    @ContributesAndroidInjector(modules = [InfoFragmentModule::class])
    fun infoFragmentInjector(): InfoFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    fun searchFragmentInjector(): SearchFragment

    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    fun filterFragmentInjector(): FilterFragment

}

@Module
class ProvidesBikeIndexActivityModule {

    @Provides
    fun provideFragmentManager(context: BikeIndexActivity): FragmentManager
            = context.supportFragmentManager

}