package com.sonkins.bikeindex.presentation.ui.filter

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.R.id.textViewColor
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_filter.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 03 April 2018.
 */
class FilterFragment : BaseFragment() {

    @Inject lateinit var router: Router

    lateinit var filterModel: FilterModel

    companion object {

        const val ARG_FILTER = "arg_filter"
        const val FILTER_REQUEST_CODE = 1

        fun newInstance(filterModel: FilterModel): FilterFragment {
            val args = Bundle()
            args.putParcelable(ARG_FILTER, filterModel)
            val fragment = FilterFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var applyFilterListener: ApplyFilterListener

    override fun showLoading() {
        TODO("not implemented")
    }

    override fun hideLoading() {
        TODO("not implemented")
    }

    override fun showError(message: String) {
        TODO("not implemented")
    }

    override fun onAttach(context: Context?) {
        if (context is ApplyFilterListener) {
            applyFilterListener = context
        } else {
            throw IllegalStateException("Activity must implement ApplyFilterListener")
        }

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.filter)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        filterModel = arguments?.getParcelable(ARG_FILTER)!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderFilter()

        filterManufacture.setOnClickListener {
            router.showManufacturersFragment(this, FILTER_REQUEST_CODE, filterModel)
        }

        filterColor.setOnClickListener {
            router.showColorsFragment(this, FILTER_REQUEST_CODE, filterModel)
        }

        filterType.setOnClickListener {
            router.showTypeFragment(this, FILTER_REQUEST_CODE, filterModel)
        }

        buttonApplyFilters.setOnClickListener {
            applyFilterListener.applyFilter(filterModel)
        }

    }

    private fun renderFilter() {
        renderColor()
        renderManufacture()
        renderType()
    }

    private fun renderType() {
        textViewTypeTitle.visibility = View.GONE
        textViewTypeHint.visibility = View.VISIBLE
        textViewType.text = filterModel.type
        textViewType.visibility = View.VISIBLE
    }

    private fun renderColor() {
        if (filterModel.color == null) {
            textViewColorTitle.visibility = View.VISIBLE
            textViewColorHint.visibility = View.GONE
            textViewColor.visibility = View.GONE
        } else {
            textViewColorTitle.visibility = View.GONE
            textViewColorHint.visibility = View.VISIBLE
            textViewColor.text = filterModel.color!!.name
            textViewColor.visibility = View.VISIBLE
        }
    }

    private fun renderManufacture() {
        if (filterModel.manufacture == null) {
            textViewManufactureTitle.visibility = View.VISIBLE
            textViewManufactureHint.visibility = View.GONE
            textViewManufacture.visibility = View.GONE
        } else {
            textViewManufactureTitle.visibility = View.GONE
            textViewManufactureHint.visibility = View.VISIBLE
            textViewManufacture.text = filterModel.manufacture!!.name
            textViewManufacture.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == FILTER_REQUEST_CODE) {
                this.filterModel = data?.getParcelableExtra(ARG_FILTER)!!
                renderFilter()
            }
        }
    }

    interface ApplyFilterListener {
        fun applyFilter(filterModel: FilterModel)
    }

}