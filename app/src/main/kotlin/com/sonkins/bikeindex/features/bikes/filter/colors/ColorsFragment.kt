/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.features.bikes.filter.colors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.exception.ConnectionException
import com.sonkins.bikeindex.core.extension.activityViewModel
import com.sonkins.bikeindex.core.extension.gone
import com.sonkins.bikeindex.core.extension.navigateUp
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.viewModel
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.DataState
import com.sonkins.bikeindex.features.bikes.filter.FilterViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_colors.*
import kotlinx.android.synthetic.main.view_error_connection.*
import kotlinx.android.synthetic.main.view_error_server.*
import kotlinx.android.synthetic.main.view_progress.*
import javax.inject.Inject

class ColorsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var colorsAdapter: ColorsAdapter

    private lateinit var colorsViewModel: ColorsViewModel
    private lateinit var filterViewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        colorsViewModel = viewModel(viewModelFactory) {
            observe(colorsDataState) {
                handleDataState(it)
            }
        }

        filterViewModel = activityViewModel(viewModelFactory) {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_colors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        if (colorsViewModel.obtainCurrentData() == null) {
            loadColors()
        }
    }

    private fun initializeView() {
        toolbar.setTitle(R.string.colors)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonConnectionTryAgain.setOnClickListener { loadColors() }
        buttonServerTryAgain.setOnClickListener { loadColors() }

        colorsAdapter.onColorClick = {
            filterViewModel.setColor(it)
            navigateUp()
        }

        recyclerViewColors.layoutManager = LinearLayoutManager(context)
        recyclerViewColors.adapter = colorsAdapter
    }

    private fun loadColors() {
        showProgress()
        colorsViewModel.loadColors()
    }

    private fun handleDataState(colorsDataState: DataState<List<ColorModel>>?) {
        hideProgress()

        when (colorsDataState) {
            is DataState.Success -> renderColors(colorsDataState.data)
            is DataState.Error -> handleError(colorsDataState.exception)
        }
    }

    private fun renderColors(colors: List<ColorModel>) {
        colorsAdapter.submitList(colors)
    }

    private fun handleError(exception: Exception) {
        when (exception) {
            is ConnectionException -> layoutConnectionError.visible()
            else -> layoutServerError.visible()
        }

        Crashlytics.logException(exception)
    }

    private fun showProgress() {
        hideErrors()
        progressBarGlobal.visible()
    }

    private fun hideProgress() {
        progressBarGlobal.gone()
        hideErrors()
    }

    private fun hideErrors() {
        layoutServerError.gone()
        layoutConnectionError.gone()
    }
}