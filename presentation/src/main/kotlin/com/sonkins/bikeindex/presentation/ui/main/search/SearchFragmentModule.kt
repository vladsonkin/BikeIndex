package com.sonkins.bikeindex.presentation.ui.main.search

import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
@Module
open class SearchFragmentModule {

    // TODO: Maybe @Binds? But we need to separate with @Provides or make it static
    @Provides
    fun provideSearchView(searchFragment: SearchFragment): SearchContract.View = searchFragment

    @Provides
    fun provideSearchPresenter(view: SearchContract.View): SearchContract.Presenter = SearchPresenter(view)

}