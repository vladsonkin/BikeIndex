package com.sonkins.bikeindex.presentation.ui.main.bikes

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.BikesModel
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import com.sonkins.bikeindex.presentation.util.ui.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.fragment_bikes.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class BikesFragment : BaseFragment(), BikesContract.View {

    @Inject lateinit var bikesPresenter: BikesPresenter
    @Inject lateinit var bikesAdapter: BikesAdapter

    companion object {
        const val FILTER_REQUEST_CODE = 1
        const val ARG_FILTER = "arg_filter"
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.bikes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_filter -> bikesPresenter.filterClick(this, FILTER_REQUEST_CODE)
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bikes, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewBikes.layoutManager = LinearLayoutManager(context)

        bikesAdapter.setLoadMoreListener(bikesPresenter)

        recyclerViewBikes.adapter = bikesAdapter


        endlessRecyclerOnScrollListener = object: EndlessRecyclerOnScrollListener(
                recyclerViewBikes.layoutManager as LinearLayoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                bikesAdapter.showLoading(page)
            }

        }

        recyclerViewBikes.addOnScrollListener(endlessRecyclerOnScrollListener)

        bikesPresenter.loadBikes()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            val filterModel = data?.getParcelableExtra<FilterModel>(ARG_FILTER)!!
            filterModel.page = 1

            bikesPresenter.loadBikes(filterModel)
        }
    }
}