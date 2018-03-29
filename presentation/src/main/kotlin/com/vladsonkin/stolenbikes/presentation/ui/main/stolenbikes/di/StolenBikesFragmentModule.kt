package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.di

import com.vladsonkin.stolenbikes.domain.interactor.bike.GetStolenBikes
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesContract
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesFragment
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesPresenter
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