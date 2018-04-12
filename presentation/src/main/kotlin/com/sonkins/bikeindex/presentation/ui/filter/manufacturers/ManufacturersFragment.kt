package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.ManufacturersModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import com.sonkins.bikeindex.presentation.util.ui.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_manufacturers.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
class ManufacturersFragment : BaseFragment(), ManufacturersContract.View, BasePaginationAdapter.LoadMoreListener {

    @Inject lateinit var manufacturersPresenter: ManufacturersPresenter
    @Inject lateinit var manufacturersAdapter: ManufacturersAdapter

    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener

    override fun showError(message: String) {
        Timber.i("error %s", message)
    }

    override fun showLoading() {
        progressBarGlobal?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarGlobal?.visibility = View.GONE
    }

    override fun showManufacturers(manufacturersModel: ManufacturersModel, nextPage: Boolean, isMore: Boolean) {
        manufacturersModel.manufacturers?.let {
            if (nextPage) {
                manufacturersAdapter.addNextPage(it, isMore)
            } else {
                manufacturersAdapter.updateData(it)
                recyclerViewManufacturers.scrollToPosition(0)
                endlessRecyclerOnScrollListener.resetState()
            }

            Timber.i("success %s", it.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_manufacturers, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(getString(R.string.manufacturers))
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewManufacturers.layoutManager = LinearLayoutManager(context)

        manufacturersAdapter.setListener(this)

        recyclerViewManufacturers.adapter = manufacturersAdapter

        endlessRecyclerOnScrollListener = object: EndlessRecyclerOnScrollListener(
                recyclerViewManufacturers.layoutManager as LinearLayoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                manufacturersAdapter.showLoading(page)
            }

        }

        recyclerViewManufacturers.addOnScrollListener(endlessRecyclerOnScrollListener)

        manufacturersPresenter.getManufacturers(1)
    }

    override fun loadMore(page: Int) {
        manufacturersPresenter.getManufacturers(page)
    }
}