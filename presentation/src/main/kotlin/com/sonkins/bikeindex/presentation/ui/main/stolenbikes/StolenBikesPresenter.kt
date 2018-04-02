package com.sonkins.bikeindex.presentation.ui.main.bikeindex

import com.sonkins.bikeindex.domain.interactor.bike.GetStolenBikes
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
open class StolenBikesPresenter @Inject constructor(
        override val view: StolenBikesContract.View,
        private val getStolenBikesUseCase: GetStolenBikes)
    : BasePresenter<StolenBikesContract.View>(view), StolenBikesContract.Presenter {

    override fun getStolenBikes(page: Int, perPage: Int, location: String) {
        val nextPage = page > 1

        val getStolenBikes = getStolenBikesUseCase.execute(
                GetStolenBikes.Params.forData(page, perPage, location))
                .doOnSubscribe { showLoading() }
                .doFinally { hideLoading() }
                .subscribe({ stolenBikes -> showStolenBikes(stolenBikes, nextPage) }, this::showError)

        unsubscribeOnDestroy(getStolenBikes)
    }

    private fun showStolenBikes(stolenBikes: List<Bike>, nextPage: Boolean) {
        view.showStolenBikes(stolenBikes, nextPage)
    }

}