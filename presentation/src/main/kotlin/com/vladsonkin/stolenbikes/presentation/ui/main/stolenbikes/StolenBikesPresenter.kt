package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import com.vladsonkin.stolenbikes.domain.interactor.bike.GetStolenBikes
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.presentation.ui.base.BasePresenter
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
        val getStolenBikesDisposable = getStolenBikesUseCase.execute(
                GetStolenBikes.Params.forData(page, perPage, location))
                .doOnSubscribe { showLoading() }
                .doFinally { hideLoading() }
                .subscribe(this::showStolenBikes, this::showError)

        unsubscribeOnDestroy(getStolenBikesDisposable)
    }

    private fun showStolenBikes(stolenBikes: List<Bike>) {
        view.showStolenBikes(stolenBikes)
    }

}