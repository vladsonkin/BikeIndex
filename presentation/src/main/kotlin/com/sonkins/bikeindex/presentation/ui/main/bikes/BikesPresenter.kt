package com.sonkins.bikeindex.presentation.ui.main.bikes

import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.presentation.mapper.BikesModelDataMapper
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
open class BikesPresenter @Inject constructor(override val view: BikesContract.View)
    : BasePresenter<BikesContract.View>(view), BikesContract.Presenter {

    @Inject lateinit var router: Router
    @Inject lateinit var getBikesUseCase: GetBikes
    @Inject lateinit var bikesModelDataMapper: BikesModelDataMapper

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

    override fun filterClick() {
        router.startFilterActivity()
    }

}