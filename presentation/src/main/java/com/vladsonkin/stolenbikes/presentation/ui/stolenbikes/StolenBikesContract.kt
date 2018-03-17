package com.vladsonkin.stolenbikes.presentation.ui.stolenbikes

import com.vladsonkin.stolenbikes.domain.model.Bike

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
interface StolenBikesContract {

    interface View {
        fun showStolenBikes(bikes: List<Bike>)

        fun showError(message: String)
    }

    interface Presenter {
        fun searchStolenBikes(page: Int, perPage: Int, location: String, distance: String)
    }

}