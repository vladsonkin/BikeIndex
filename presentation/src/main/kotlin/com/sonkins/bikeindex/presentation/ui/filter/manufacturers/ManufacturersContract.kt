package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import com.sonkins.bikeindex.presentation.model.ManufacturersModel
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
interface ManufacturersContract {

    interface View: MvpView {
        fun showManufacturers(manufacturersModel: ManufacturersModel, nextPage: Boolean, isMore: Boolean = true)
    }

    interface Presenter: MvpPresenter {
        fun getManufacturers(page: Int)
    }

}