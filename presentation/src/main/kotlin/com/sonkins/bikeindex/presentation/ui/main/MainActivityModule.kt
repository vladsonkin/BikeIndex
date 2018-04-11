package com.sonkins.bikeindex.presentation.ui.main

import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragmentModule
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragment
import com.sonkins.bikeindex.presentation.ui.main.info.InfoFragmentModule
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragment
import com.sonkins.bikeindex.presentation.ui.main.search.SearchFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 03 April 2018.
 */
@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [BikesFragmentModule::class])
    fun bikesFragmentInjector(): BikesFragment

    @ContributesAndroidInjector(modules = [InfoFragmentModule::class])
    fun infoFragmentInjector(): InfoFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    fun searchFragmentInjector(): SearchFragment

    @Binds
    fun bindContext(context: MainActivity): BaseActivity
}