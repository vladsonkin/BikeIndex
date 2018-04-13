package com.sonkins.bikeindex.presentation.ui.filter.colors

import com.sonkins.bikeindex.domain.interactor.filter.GetColors
import com.sonkins.bikeindex.presentation.mapper.ColorsModelDataMapper
import com.sonkins.bikeindex.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
class ColorsPresenter @Inject constructor(override val view: ColorsContract.View)
    : BasePresenter<ColorsContract.View>(view), ColorsContract.Presenter {

    @Inject lateinit var getColorsUseCase: GetColors
    @Inject lateinit var colorsModelDataMapper: ColorsModelDataMapper

    override fun getColors() {
        val getColors = getColorsUseCase.execute(null)
                .map(colorsModelDataMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading() }
                .doFinally { hideLoading() }
                .subscribe({
                    view.showColors(it)
                }, this::showError)

        unsubscribeOnDestroy(getColors)
    }

}