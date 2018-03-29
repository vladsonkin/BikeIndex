package com.vladsonkin.stolenbikes.presentation.ui.base

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
interface MvpPresenter {

    fun onAttach()

    fun onDetach()

    fun showError(exception: Throwable)

    fun showLoading()

    fun hideLoading()

}