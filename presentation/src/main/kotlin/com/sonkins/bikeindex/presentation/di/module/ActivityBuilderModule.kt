package com.sonkins.bikeindex.presentation.di.module

import com.sonkins.bikeindex.presentation.di.ActivityScope
import com.sonkins.bikeindex.presentation.ui.BikeIndexActivity
import com.sonkins.bikeindex.presentation.ui.BikeIndexActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BikeIndexActivityModule::class])
    abstract fun bindMainActivity(): BikeIndexActivity

}