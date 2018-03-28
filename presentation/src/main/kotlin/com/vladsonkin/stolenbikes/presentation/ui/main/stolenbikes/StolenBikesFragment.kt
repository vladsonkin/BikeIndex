package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
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
        val view = inflater.inflate(R.layout.fragment_stolen_bikes, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_stolen_bikes)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
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