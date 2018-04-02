package com.sonkins.bikeindex.presentation.ui.main.bikes

import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
open class BikesPresenter @Inject constructor(
        override val view: BikesContract.View,
        private val getBikesUseCase: GetBikes)
    : BasePresenter<BikesContract.View>(view), BikesContract.Presenter {

    override fun getStolenBikes(page: Int) {
        val nextPage = page > 1

        val getStolenBikes = getBikesUseCase.execute(
                GetBikes.Params.forData(page))
                .doOnSubscribe { if (!nextPage) showLoading() }
                .doFinally { if (!nextPage) hideLoading() }
                .subscribe(
                        { stolenBikes -> showStolenBikes(stolenBikes, nextPage) },
                        this::showError
                )

        unsubscribeOnDestroy(getStolenBikes)
    }

    private fun showStolenBikes(stolenBikes: List<Bike>, nextPage: Boolean) {
        view.showStolenBikes(stolenBikes, nextPage)
    }

}