package com.sonkins.bikeindex.presentation.ui.main.bikeindex.di

import com.sonkins.bikeindex.presentation.ui.main.bikeindex.StolenBikesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
@Module
abstract class StolenBikesFragmentBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(StolenBikesFragmentModule::class))
    abstract fun provideStolenBikesFragmentFactory(): StolenBikesFragment

}