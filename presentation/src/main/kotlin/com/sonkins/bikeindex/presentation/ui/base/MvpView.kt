package com.sonkins.bikeindex.presentation.ui.base

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun showError(message: String)

}