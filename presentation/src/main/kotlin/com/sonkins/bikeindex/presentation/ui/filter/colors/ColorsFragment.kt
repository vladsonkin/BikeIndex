package com.sonkins.bikeindex.presentation.ui.filter.colors

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.ColorsModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_colors.*
import kotlinx.android.synthetic.main.view_progress.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
class ColorsFragment : BaseFragment(), ColorsContract.View {

    @Inject lateinit var colorsPresenter: ColorsPresenter
    @Inject lateinit var colorsAdapter: ColorsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_colors, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(getString(R.string.colors))
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewColors.layoutManager = LinearLayoutManager(context)
        recyclerViewColors.adapter = colorsAdapter

        colorsPresenter.getColors()
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

    override fun showColors(colorsModel: ColorsModel) {
        colorsAdapter.setData(colorsModel.colors)
    }
}