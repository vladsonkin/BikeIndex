package com.sonkins.bikeindex.presentation.ui.main.bikes

import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.mapper.BikesModelDataMapper
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
open class BikesPresenter @Inject constructor(override val view: BikesContract.View)
    : BasePresenter<BikesContract.View>(view), BikesContract.Presenter, BasePaginationAdapter.LoadMoreListener  {

    private lateinit var filter: Filter

    override fun loadMore(page: Int) {
        filter.page = page
        loadBikes(filter)
    }

    @Inject lateinit var router: Router
    @Inject lateinit var getBikesUseCase: GetBikes
    @Inject lateinit var bikesModelDataMapper: BikesModelDataMapper

    override fun loadBikes(filter: Filter) {

        this.filter = filter

        val nextPage = this.filter.page > 1

        val getStolenBikes = getBikesUseCase.execute(
                GetBikes.Params.forFilter(this.filter))
                .map(bikesModelDataMapper::transform )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (!nextPage) showLoading()
                }
                .doFinally {
                    if (!nextPage) hideLoading()
                }
                .subscribe({
                    view.showBikes(it, nextPage)
                }, this::showError)

        unsubscribeOnDestroy(getStolenBikes)
    }

    override fun filterClick(fragment: Fragment, requestCode: Int) {
        router.startFilterActivity(fragment, requestCode, filter)
    }

}