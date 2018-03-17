package com.vladsonkin.stolenbikes.presentation.ui.stolenbikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladsonkin.stolenbikes.domain.interactor.bike.SearchStolenBikes
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class StolenBikesFragment : BaseFragment() {

    @Inject lateinit var searchStolenBikes: SearchStolenBikes

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stolen_bikes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBikes()
    }

    private fun searchBikes() {
        searchStolenBikes.execute(SearchStolenBikes.Params.forData(1, 1, "Test", "Test"))
                .subscribe(this::getBikesSuccess, this::showError)
    }

    private fun getBikesSuccess(bikes: List<Bike>) {
        Timber.i("success %s", bikes.toString())
    }

    private fun showError(exception: Throwable) {
        Timber.i("error")
    }
}