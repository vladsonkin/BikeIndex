package com.sonkins.bikeindex.presentation.ui.bikes

import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.model.BikeModel
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
interface BikesContract {

    interface View : MvpView {
        fun showBikes(bikes: List<BikeModel>, nextPage: Boolean)
    }

    interface Presenter : MvpPresenter {
        fun getBikes(page: Int)
    }

}