package com.vladsonkin.stolenbikes.presentation.ui.main.search.di

import com.vladsonkin.stolenbikes.presentation.ui.main.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
@Module
abstract class SearchFragmentBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(SearchFragmentModule::class))
    abstract fun provideSearchFragmentFactory(): SearchFragment

}