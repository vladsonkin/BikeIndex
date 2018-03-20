package com.vladsonkin.stolenbikes.presentation.di.module

import com.vladsonkin.stolenbikes.presentation.di.PerActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.MainActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.di.StolenBikesFragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(StolenBikesFragmentBuilderModule::class))
    abstract fun bindMainActivity(): MainActivity

}