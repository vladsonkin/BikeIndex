package com.sonkins.bikeindex.presentation.ui.main.bikeindex.di

import com.sonkins.bikeindex.domain.interactor.bike.GetStolenBikes
import com.sonkins.bikeindex.presentation.ui.main.bikeindex.StolenBikesContract
import com.sonkins.bikeindex.presentation.ui.main.bikeindex.StolenBikesFragment
import com.sonkins.bikeindex.presentation.ui.main.bikeindex.StolenBikesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
@Module
open class StolenBikesFragmentModule {

    @Provides
    internal fun provideStolenBikesView(stolenBikesFragment: StolenBikesFragment)
            : StolenBikesContract.View {
        return stolenBikesFragment
    }

    @Provides
    internal fun provideStolenBikesPresenter(
            view: StolenBikesContract.View,
            getStolenBikes: GetStolenBikes): StolenBikesContract.Presenter {
        return StolenBikesPresenter(view, getStolenBikes)
    }

}