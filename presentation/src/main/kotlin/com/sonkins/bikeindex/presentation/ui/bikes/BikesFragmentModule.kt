package com.sonkins.bikeindex.presentation.ui.bikes

import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
@Module
open class BikesFragmentModule {

    // TODO: Maybe @Binds? But we need to separate with @Provides or make it static
    @Provides
    fun provideBikesView(bikesFragment: BikesFragment): BikesContract.View = bikesFragment

    @Provides
    fun provideBikesPresenter(
            view: BikesContract.View,
            getBikes: GetBikes): BikesContract.Presenter = BikesPresenter(view, getBikes)

}