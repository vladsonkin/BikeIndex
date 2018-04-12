package com.sonkins.bikeindex.presentation.ui.main.bikes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.BikesModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import com.sonkins.bikeindex.presentation.util.ui.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_bikes.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class BikesFragment : BaseFragment(), BikesContract.View, BasePaginationAdapter.LoadMoreListener {

    @Inject lateinit var bikesPresenter: BikesPresenter
    @Inject lateinit var bikesAdapter: BikesAdapter

    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener

    override fun showBikes(bikesModel: BikesModel, nextPage: Boolean, isMore: Boolean) {

        bikesModel.bikes?.let {
            if (nextPage) {
                bikesAdapter.addNextPage(it, isMore)
            } else {
                bikesAdapter.updateData(it)
                recyclerViewBikes.scrollToPosition(0)
                endlessRecyclerOnScrollListener.resetState()
            }

            Timber.i("success %s", it.toString())
        }

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
            bikesPresenter.filterClick()
        }

        recyclerViewBikes.layoutManager = LinearLayoutManager(context)

        bikesAdapter.setListener(this)

        recyclerViewBikes.adapter = bikesAdapter


        endlessRecyclerOnScrollListener = object: EndlessRecyclerOnScrollListener(
                recyclerViewBikes.layoutManager as LinearLayoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                bikesAdapter.showLoading(page)
            }

        }

        recyclerViewBikes.addOnScrollListener(endlessRecyclerOnScrollListener)

        bikesPresenter.getBikes(1)

    }

    override fun loadMore(page: Int) {
        bikesPresenter.getBikes(page)
    }
}