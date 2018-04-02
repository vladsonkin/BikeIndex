package com.sonkins.bikeindex.presentation.di.module

import com.sonkins.bikeindex.presentation.di.PerActivity
import com.sonkins.bikeindex.presentation.ui.main.info.di.InfoFragmentBuilderModule
import com.sonkins.bikeindex.presentation.ui.main.search.di.SearchFragmentBuilderModule
import com.sonkins.bikeindex.presentation.ui.main.MainActivity
import com.sonkins.bikeindex.presentation.ui.main.bikeindex.di.StolenBikesFragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(
            StolenBikesFragmentBuilderModule::class,
            SearchFragmentBuilderModule::class,
            InfoFragmentBuilderModule::class))
    abstract fun bindMainActivity(): MainActivity

}