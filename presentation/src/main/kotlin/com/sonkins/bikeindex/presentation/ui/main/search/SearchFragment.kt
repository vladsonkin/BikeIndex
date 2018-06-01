package com.sonkins.bikeindex.presentation.ui.main.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.BikesModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import com.sonkins.bikeindex.presentation.ui.main.bikes.BikesAdapter
import com.sonkins.bikeindex.presentation.util.ui.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
class SearchFragment : BaseFragment(), SearchContract.View {

    @Inject
    lateinit var bikesAdapter: BikesAdapter
    @Inject
    lateinit var searchPresenter: SearchPresenter

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

    override fun showLoading() {
        progressBarGlobal?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarGlobal?.visibility = View.GONE
    }

    override fun showError(message: String) {
        Timber.i("error %s", message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editSearch.requestFocus()

        recyclerViewBikes.layoutManager = LinearLayoutManager(context)
        bikesAdapter.setLoadMoreListener(searchPresenter)
        recyclerViewBikes.adapter = bikesAdapter

        endlessRecyclerOnScrollListener = object: EndlessRecyclerOnScrollListener(
                recyclerViewBikes.layoutManager as LinearLayoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                bikesAdapter.showLoading(page)
            }

        }

        recyclerViewBikes.addOnScrollListener(endlessRecyclerOnScrollListener)
    }

}