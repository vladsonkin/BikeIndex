package com.sonkins.bikeindex.presentation.ui.main.bikes.di

import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesContract
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesFragment
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
@Module
open class BikesFragmentModule {

    @Provides
    internal fun provideStolenBikesView(bikesFragment: BikesFragment)
            : BikesContract.View {
        return bikesFragment
    }

    @Provides
    internal fun provideStolenBikesPresenter(
            view: BikesContract.View,
            getBikes: GetBikes): BikesContract.Presenter {
        return BikesPresenter(view, getBikes)
    }

}