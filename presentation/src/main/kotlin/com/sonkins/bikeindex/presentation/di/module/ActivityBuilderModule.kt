package com.sonkins.bikeindex.presentation.di.module

import com.sonkins.bikeindex.presentation.di.ActivityScope
import com.sonkins.bikeindex.presentation.ui.filter.FilterActivity
import com.sonkins.bikeindex.presentation.ui.filter.FilterActivityModule
import com.sonkins.bikeindex.presentation.ui.main.MainActivity
import com.sonkins.bikeindex.presentation.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FilterActivityModule::class])
    abstract fun bindFilterActivity(): FilterActivity

}