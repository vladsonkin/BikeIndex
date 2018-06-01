package com.sonkins.bikeindex.presentation.ui.main.search

import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.interactor.bike.GetBikes
import com.sonkins.bikeindex.presentation.mapper.BikesModelDataMapper
import com.sonkins.bikeindex.presentation.mapper.FilterMapper
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 02 May 2018.
 */
class SearchPresenter @Inject constructor(override val view: SearchContract.View)
    : BasePresenter<SearchContract.View>(view), SearchContract.Presenter,
        BasePaginationAdapter.LoadMoreListener  {

    private lateinit var filterModel: FilterModel

    override fun loadMore(page: Int) {
        filterModel.page = page
        loadBikes(filterModel)
    }

    @Inject lateinit var router: Router
    @Inject lateinit var getBikesUseCase: GetBikes
    @Inject lateinit var bikesModelDataMapper: BikesModelDataMapper
    @Inject lateinit var filterMapper: FilterMapper

    override fun loadBikes(filterModel: FilterModel) {

        this.filterModel = filterModel

        val nextPage = this.filterModel.page > 1

        val getStolenBikes = getBikesUseCase.execute(
                GetBikes.Params.forFilter(filterMapper.transform(this.filterModel)))
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

}