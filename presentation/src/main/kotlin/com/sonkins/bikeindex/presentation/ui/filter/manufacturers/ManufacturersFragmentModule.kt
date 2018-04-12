package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
@Module
open class ManufacturersFragmentModule {

    @Provides
    fun provideManufacturersView(manufacturersFragment: ManufacturersFragment) : ManufacturersContract.View = manufacturersFragment

    @Provides
    fun provideManufacturersPresenter(view: ManufacturersContract.View) : ManufacturersContract.Presenter = ManufacturersPresenter(view)

}