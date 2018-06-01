package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.model.ManufactureModel
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
class ManufacturersFragment : BaseFragment(), ManufacturersContract.View,
        BasePaginationAdapter.LoadMoreListener, ManufacturersAdapter.ManufactureClickListener {

    @Inject lateinit var manufacturersPresenter: ManufacturersPresenter
    @Inject lateinit var manufacturersAdapter: ManufacturersAdapter

    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener
    private lateinit var filterModel: FilterModel

    companion object {

        const val ARG_FILTER = "arg_filter"

        fun newInstance(filterModel: FilterModel): ManufacturersFragment {
            val args = Bundle()
            args.putParcelable(ARG_FILTER, filterModel)
            val fragment = ManufacturersFragment()
            fragment.arguments = args
            return fragment
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

        filterModel = arguments?.getParcelable(ARG_FILTER)!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewManufacturers.layoutManager = LinearLayoutManager(context)

        manufacturersAdapter.setClickListener(this)
        manufacturersAdapter.setLoadMoreListener(this)

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

    override fun manufactureClick(manufactureModel: ManufactureModel) {
        filterModel.manufacture = manufactureModel

        val intent = Intent(context, ManufacturersFragment::class.java)
        intent.putExtra(ARG_FILTER, filterModel)
        activity?.onBackPressed()
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }
}