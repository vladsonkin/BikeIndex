package com.vladsonkin.stolenbikes.presentation.di.module

import com.vladsonkin.stolenbikes.presentation.di.PerActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.info.InfoActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.info.di.InfoFragmentBuilderModule
import com.vladsonkin.stolenbikes.presentation.ui.main.search.SearchActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.search.di.SearchFragmentBuilderModule
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesActivity
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
    abstract fun bindStolenBikesActivity(): StolenBikesActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SearchFragmentBuilderModule::class))
    abstract fun bindSearchActivity(): SearchActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(InfoFragmentBuilderModule::class))
    abstract fun bindInfoActivity(): InfoActivity

}