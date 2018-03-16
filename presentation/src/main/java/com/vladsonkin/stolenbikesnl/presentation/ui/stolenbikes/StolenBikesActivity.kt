package com.vladsonkin.stolenbikesnl.presentation.ui.stolenbikes

import android.os.Bundle
import android.util.Log
import com.vladsonkin.stolenbikesnl.domain.interactor.bike.SearchStolenBikes
import com.vladsonkin.stolenbikesnl.domain.model.Bike
import com.vladsonkin.stolenbikesnl.presentation.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_stolen_bikes.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class StolenBikesActivity : DaggerAppCompatActivity() {

    @Inject lateinit var searchStolenBikes: SearchStolenBikes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stolen_bikes)

        setSupportActionBar(toolbar)
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
