package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.presentation.ui.base.MvpPresenter
import com.vladsonkin.stolenbikes.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
interface StolenBikesContract {

    interface View : MvpView {
        fun showStolenBikes(bikes: List<Bike>, nextPage: Boolean)
    }

    interface Presenter : MvpPresenter {
        fun getStolenBikes(page: Int, perPage: Int, location: String)
    }

}