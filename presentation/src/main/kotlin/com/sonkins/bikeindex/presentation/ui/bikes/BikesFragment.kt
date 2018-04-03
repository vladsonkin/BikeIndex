package com.sonkins.bikeindex.presentation.ui.bikes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import com.sonkins.bikeindex.presentation.ui.filter.FilterFragment
import com.sonkins.bikeindex.presentation.util.ui.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_bikes.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class BikesFragment : BaseFragment(), BikesContract.View, BikesAdapter.LoadMoreListener {

    @Inject lateinit var bikesPresenter: BikesPresenter
    private lateinit var bikesAdapter: BikesAdapter

    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener

    override fun showStolenBikes(bikes: List<Bike>, nextPage: Boolean) {

        if (nextPage) {
            bikesAdapter.dismissLoading()
            bikesAdapter.addNextPage(bikes)
            bikesAdapter.setMore(true)
        } else {
            bikesAdapter.updateData(bikes)
            endlessRecyclerOnScrollListener.resetState()
        }

        Timber.i("success %s", bikes.toString())
    }

    override fun showError(message: String) {
        Timber.i("error %s", message)
    }

    override fun showLoading() {
        progressBarGlobal?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarGlobal?.visibility = View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bikes, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_bikes)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFilter.setOnClickListener {
            (activity as BaseActivity).addFragment(R.id.fragmentContainer, FilterFragment())
        }

        recyclerViewStolenBikes.layoutManager = LinearLayoutManager(context)

        bikesAdapter = BikesAdapter(this)

        recyclerViewStolenBikes.adapter = bikesAdapter


        endlessRecyclerOnScrollListener = object: EndlessRecyclerOnScrollListener(
                recyclerViewStolenBikes.layoutManager as LinearLayoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                bikesAdapter.showLoading(page)
            }

        }

        recyclerViewStolenBikes.addOnScrollListener(endlessRecyclerOnScrollListener)

        bikesPresenter.getStolenBikes(1)
    }


    override fun loadMore(page: Int) {
        bikesPresenter.getStolenBikes(page)
    }
}