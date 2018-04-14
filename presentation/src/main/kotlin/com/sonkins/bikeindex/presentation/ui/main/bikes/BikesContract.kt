package com.sonkins.bikeindex.presentation.ui.main.bikes

import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.model.BikesModel
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
interface BikesContract {

    interface View : MvpView {
        fun showBikes(bikesModel: BikesModel, nextPage: Boolean, isMore: Boolean = true)
    }

    interface Presenter : MvpPresenter {
        fun loadBikes(filter: Filter = Filter())

        fun filterClick(fragment: Fragment, requestCode: Int)
    }

}