package com.sonkins.bikeindex.presentation.ui.main.bikes.di

import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
@Module
abstract class BikesFragmentBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(BikesFragmentModule::class))
    abstract fun provideStolenBikesFragmentFactory(): BikesFragment

}