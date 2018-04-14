package com.sonkins.bikeindex.presentation.ui.filter

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewFilterManufacturer.setOnClickListener {
            router.showManufacturersFragment()
        }

        textViewFilterColor.setOnClickListener {
            router.showColorsFragment()
        }

        buttonApplyFilters.setOnClickListener {
            applyFilterListener.applyFilter()
        }

    }

    interface ApplyFilterListener {
        fun applyFilter()
    }

}