package com.sonkins.bikeindex.presentation.ui.filter.colors

import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
@Module
open class ColorsFragmentModule {

    @Provides
    fun provideColorsView(colorsFragment: ColorsFragment) : ColorsContract.View = colorsFragment

    @Provides
    fun provideColorsPresenter(view: ColorsContract.View) : ColorsContract.Presenter = ColorsPresenter(view)

}