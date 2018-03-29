package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_stolen_bikes.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class StolenBikesFragment : BaseFragment(), StolenBikesContract.View {

    @Inject lateinit var stolenBikesPresenter: StolenBikesPresenter
    private lateinit var bikesAdapter: StolenBikesAdapter

    override fun showStolenBikes(bikes: List<Bike>) {
        bikesAdapter.setData(bikes)
        Timber.i("success %s", bikes.toString())
    }

    override fun showError(message: String) {
        Timber.i("error %s", message)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stolen_bikes, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_stolen_bikes)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewStolenBikes.layoutManager = LinearLayoutManager(context)

        bikesAdapter = StolenBikesAdapter()

        recyclerViewStolenBikes.adapter = bikesAdapter

        stolenBikesPresenter.getStolenBikes(1, 25, "52.379189,4.899431")
    }
}