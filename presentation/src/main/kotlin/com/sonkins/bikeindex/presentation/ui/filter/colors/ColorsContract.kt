package com.sonkins.bikeindex.presentation.ui.filter.colors

import com.sonkins.bikeindex.presentation.model.ColorsModel
import com.sonkins.bikeindex.presentation.ui.base.MvpPresenter
import com.sonkins.bikeindex.presentation.ui.base.MvpView

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
interface ColorsContract {

    interface View: MvpView {
        fun showColors(colorsModel: ColorsModel)
    }

    interface Presenter: MvpPresenter {
        fun getColors()
    }

}