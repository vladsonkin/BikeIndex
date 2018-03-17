package com.vladsonkin.stolenbikes.presentation.ui.stolenbikes.di

import com.vladsonkin.stolenbikes.presentation.ui.stolenbikes.StolenBikesFragment
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