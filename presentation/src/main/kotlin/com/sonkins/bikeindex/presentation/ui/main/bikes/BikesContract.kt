package com.sonkins.bikeindex.presentation.ui.main.bikes

import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
interface BikesContract {

    interface View : MvpView {
        fun showStolenBikes(bikes: List<Bike>, nextPage: Boolean)
    }

    interface Presenter : MvpPresenter {
        fun getStolenBikes(page: Int)
    }

}