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

package com.sonkins.bikeindex.features.bikes.filter.manufacturers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.exception.ConnectionException
import com.sonkins.bikeindex.core.extension.action
import com.sonkins.bikeindex.core.extension.activityViewModel
import com.sonkins.bikeindex.core.extension.invisible
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.extension.navigateUp
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.snack
import com.sonkins.bikeindex.core.extension.viewModel
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.BaseFragment
import com.sonkins.bikeindex.core.platform.DataState
import com.sonkins.bikeindex.core.platform.EndlessOnScrollListener
import com.sonkins.bikeindex.features.bikes.filter.FilterViewModel
import kotlinx.android.synthetic.main.fragment_manufacturers.*
import kotlinx.android.synthetic.main.view_error_connection.*
import kotlinx.android.synthetic.main.view_error_server.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.coroutines.experimental.delay
import javax.inject.Inject

class ManufacturersFragment : BaseFragment() {

    @Inject
    lateinit var manufacturersAdapter: ManufacturersAdapter

    private lateinit var manufacturersViewModel: ManufacturersViewModel
    private lateinit var filterViewModel: FilterViewModel
    private lateinit var endlessOnScrollListener: EndlessOnScrollListener

    override fun layoutId() = R.layout.fragment_manufacturers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        manufacturersViewModel = viewModel(viewModelFactory) {
            observe(manufacturersDataState) {
                handleDataState(it)
            }
        }

        filterViewModel = activityViewModel(viewModelFactory) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()

        if (manufacturersViewModel.obtainCurrentData() == null) {
            loadManufacturers(true)
        }
    }

    private fun initializeView() {
        toolbar.setTitle(R.string.manufacturers)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        manufacturersAdapter.onManufacturerClick = {
            filterViewModel.setManufacturer(it)
            navigateUp()
        }

        manufacturersAdapter.loadMore = { manufacturersViewModel.loadMoreManufacturers() }

        buttonConnectionTryAgain.setOnClickListener { refreshManufacturers() }
        buttonServerTryAgain.setOnClickListener { refreshManufacturers() }

        recyclerViewManufacturers.layoutManager = LinearLayoutManager(context)
        endlessOnScrollListener =
            object : EndlessOnScrollListener(recyclerViewManufacturers.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(totalItemsCount: Int, view: RecyclerView) {
                    manufacturersAdapter.showLoading()
                }
            }

        recyclerViewManufacturers.adapter = manufacturersAdapter
        recyclerViewManufacturers.addOnScrollListener(endlessOnScrollListener)
    }

    /**
     * @param refresh represents if we want to refresh bike list or to add next page results
     */
    private fun loadManufacturers(refresh: Boolean) {
        showProgress()
        manufacturersViewModel.loadManufacturers(refresh)
    }

    private fun refreshManufacturers() {
        showProgress()
        manufacturersViewModel.refreshManufacturers()
    }

    private fun handleDataState(manufacturersDataState: DataState<List<ManufacturerModel>>?) {
        hideProgress()

        when (manufacturersDataState) {
            is DataState.Success -> renderManufacturers(manufacturersDataState.data)
            is DataState.Error -> handleError(manufacturersDataState.exception)
        }
    }

    private fun renderManufacturers(manufacturerModels: List<ManufacturerModel>) {
        manufacturersAdapter.updateData(manufacturerModels)

        // If data was refreshed then scroll list to the top
        if (manufacturersViewModel.refresh) {
            launchAsync {
                delay(50)
                recyclerViewManufacturers.scrollToPosition(0)
                endlessOnScrollListener.resetState()
            }

            manufacturersViewModel.refresh = false
        }
    }

    private fun handleError(exception: Exception) {
        when (exception) {
            is ConnectionException -> {
                manufacturersViewModel.obtainCurrentData()?.let { manufacturerModels ->
                    renderManufacturers(manufacturerModels)
                    snack(R.string.no_internet_connection) {
                        action(R.string.reload) {
                            this.dismiss()
                            refreshManufacturers()
                        }
                    }
                } ?: layoutConnectionError.visible()
            }

            else -> {
                manufacturersViewModel.obtainCurrentData()?.let { manufacturerModels ->
                    renderManufacturers(manufacturerModels)
                    snack(R.string.something_went_wrong) {
                        action(R.string.reload) {
                            this.dismiss()
                            refreshManufacturers()
                        }
                    }
                } ?: layoutServerError.visible()
            }
        }

        Crashlytics.logException(exception)
    }

    private fun showProgress() {
        hideErrors()
        progressBarGlobal.visible()
    }

    private fun hideProgress() {
        progressBarGlobal.invisible()
        hideErrors()
    }

    private fun hideErrors() {
        layoutServerError.invisible()
        layoutConnectionError.invisible()
    }
}