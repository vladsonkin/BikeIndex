package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import com.sonkins.bikeindex.domain.interactor.filter.GetManufacturers
import com.sonkins.bikeindex.presentation.mapper.ManufacturersModelDataMapper
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
class ManufacturersPresenter @Inject constructor(override val view: ManufacturersContract.View)
    : BasePresenter<ManufacturersContract.View>(view), ManufacturersContract.Presenter {

    @Inject lateinit var getManufacturersUseCase: GetManufacturers
    @Inject lateinit var manufacturersModelDataMapper: ManufacturersModelDataMapper

    override fun getManufacturers(page: Int) {
        val nextPage = page > 1

        val getManufacturers = getManufacturersUseCase.execute(GetManufacturers.Params.forPage(page))
                .map(manufacturersModelDataMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (!nextPage) showLoading()
                }
                .doFinally {
                    if (!nextPage) hideLoading()
                }
                .subscribe({
                    view.showManufacturers(it, nextPage)
                }, this::showError)

        unsubscribeOnDestroy(getManufacturers)
    }

}