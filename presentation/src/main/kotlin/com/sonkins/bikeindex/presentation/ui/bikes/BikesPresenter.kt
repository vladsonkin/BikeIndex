package com.sonkins.bikeindex.presentation.ui.bikes

import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.presentation.mapper.BikesModelDataMapper
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
open class BikesPresenter @Inject constructor(override val view: BikesContract.View,
                                              private val getBikesUseCase: GetBikes,
                                              private val bikesModelDataMapper: BikesModelDataMapper)
    : BasePresenter<BikesContract.View>(view), BikesContract.Presenter {

    override fun getBikes(page: Int) {
        val nextPage = page > 1

        val getStolenBikes = getBikesUseCase.execute(
                GetBikes.Params.forData(page))
                .doOnSubscribe { if (!nextPage) showLoading() }
                .doFinally { if (!nextPage) hideLoading() }
                .subscribe(
                        { bikes -> showBikes(bikes, nextPage) },
                        this::showError
                )

        unsubscribeOnDestroy(getStolenBikes)
    }

    private fun showBikes(bikes: Bikes, nextPage: Boolean) {
        view.showBikes(bikesModelDataMapper.transform(bikes), nextPage)
    }

}