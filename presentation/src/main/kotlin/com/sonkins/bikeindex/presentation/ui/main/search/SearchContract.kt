package com.sonkins.bikeindex.presentation.ui.main.search

import com.sonkins.bikeindex.presentation.model.BikesModel
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 02 May 2018.
 */
interface SearchContract {

    interface View : MvpView {
        fun showBikes(bikesModel: BikesModel, nextPage: Boolean, isMore: Boolean = true)
    }

    interface Presenter : MvpPresenter {
        fun loadBikes(filterModel: FilterModel = FilterModel())
    }

}