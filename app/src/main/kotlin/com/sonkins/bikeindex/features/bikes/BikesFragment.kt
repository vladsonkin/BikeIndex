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

package com.sonkins.bikeindex.features.bikes

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.exception.ConnectionException
import com.sonkins.bikeindex.core.extension.action
import com.sonkins.bikeindex.core.extension.activityViewModel
import com.sonkins.bikeindex.core.extension.gone
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.extension.navigate
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.snack
import com.sonkins.bikeindex.core.extension.viewModel
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.DataState
import com.sonkins.bikeindex.core.platform.EndlessOnScrollListener
import com.sonkins.bikeindex.core.platform.Event
import com.sonkins.bikeindex.features.bikes.filter.FilterModel
import com.sonkins.bikeindex.features.bikes.filter.FilterViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_bikes.*
import kotlinx.android.synthetic.main.view_error_connection.*
import kotlinx.android.synthetic.main.view_error_nothing_found.*
import kotlinx.android.synthetic.main.view_error_server.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.coroutines.experimental.delay
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView
import javax.inject.Inject

class BikesFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var bikesAdapter: BikesAdapter

    private lateinit var bikesViewModel: BikesViewModel
    private lateinit var filterViewModel: FilterViewModel
    private lateinit var endlessOnScrollListener: EndlessOnScrollListener
    private var filterBadge: Badge? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bikesViewModel = viewModel(viewModelFactory) {
            observe(navigateToFilterEvent, ::navigateToFilter)
            observe(bikesDataState, ::handleDataState)
        }

        filterViewModel = activityViewModel(viewModelFactory) {
            observe(sharedFilter) { filterEvent ->
                filterEvent?.getContentIfNotHandled {
                    loadBikes(it, true)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bikes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        with(bikesViewModel.bikesDataState) {
            this.value?.let {
                handleDataState(it)
            } ?: loadBikes(FilterModel.empty(), false)
        }
    }

    private fun initializeView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setFilterCountBadge(bikesViewModel.countActiveFilters())
        imageBtnFilter.setOnClickListener { bikesViewModel.filterClick() }

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
        swipeRefreshLayout.setOnRefreshListener { bikesViewModel.refreshBikes() }

        buttonConnectionTryAgain.setOnClickListener { refreshBikes() }
        buttonServerTryAgain.setOnClickListener { refreshBikes() }
        buttonChangeFilter.setOnClickListener { bikesViewModel.filterClick() }

        bikesAdapter.onBikeClick = {
            navigate(BikesFragmentDirections.actionBikesFragmentToBikeFragment(it.toString()))
        }

        bikesAdapter.onBikeShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, it))
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)))
        }

        bikesAdapter.loadMore = { bikesViewModel.loadMoreBikes() }

        recyclerViewBikes.layoutManager = LinearLayoutManager(context)
        endlessOnScrollListener =
            object : EndlessOnScrollListener(recyclerViewBikes.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(totalItemsCount: Int, view: RecyclerView) {
                    bikesAdapter.showLoading()
                }
            }

        recyclerViewBikes.adapter = bikesAdapter
        recyclerViewBikes.addOnScrollListener(endlessOnScrollListener)
    }

    /**
     * @param refresh represents if we want to refresh bike list or to add next page results
     */
    private fun loadBikes(filterModel: FilterModel, refresh: Boolean) {
        showProgress()
        setFilterCountBadge(filterModel.countActiveFilters())
        bikesViewModel.loadBikes(filterModel, refresh)
    }

    private fun refreshBikes() {
        showProgress()
        bikesViewModel.refreshBikes()
    }

    private fun handleDataState(bikesDataState: DataState<BikesModel>?) {
        hideProgress()

        when (bikesDataState) {
            is DataState.Success -> renderBikes(bikesDataState.data)
            is DataState.Error -> handleError(bikesDataState.exception)
        }
    }

    private fun renderBikes(bikesModel: BikesModel) {
        showBikesFoundCount(bikesModel.total)
        bikesAdapter.updateData(bikesModel.bikeModels)

        // If data was refreshed then scroll list to the top
        if (bikesViewModel.refresh) {
            launchAsync {
                delay(50)
                recyclerViewBikes.scrollToPosition(0)
                endlessOnScrollListener.resetState()
            }

            bikesViewModel.refresh = false
        }
    }

    private fun handleError(exception: Exception) {
        when (exception) {
            is NothingFoundException -> {
                bikesAdapter.updateData(emptyList())
                layoutNothingFoundFilter.visible()
            }
            is ConnectionException -> {
                bikesViewModel.obtainCurrentData()?.let { bikesModel ->
                    renderBikes(bikesModel)
                    snack(R.string.no_internet_connection) {
                        action(R.string.reload) {
                            this.dismiss()
                            refreshBikes()
                        }
                    }
                } ?: layoutConnectionError.visible()
            }
            else -> {
                bikesViewModel.obtainCurrentData()?.let { bikesModel ->
                    renderBikes(bikesModel)
                    snack(R.string.something_went_wrong) {
                        action(R.string.reload) {
                            this.dismiss()
                            refreshBikes()
                        }
                    }
                } ?: layoutServerError.visible()
            }
        }

        Crashlytics.logException(exception)
    }

    private fun navigateToFilter(event: Event<Bundle>?) {
        event?.getContentIfNotHandled { navigate(R.id.action_bikesFragment_to_filterFragment, it) }
    }

    private fun showBikesFoundCount(bikesFound: Int) {
        toolbarSubtitle.visible()
        toolbarSubtitle.text = resources.getQuantityString(R.plurals.bikes_found_count, bikesFound, bikesFound)
    }

    private fun setFilterCountBadge(number: Int) {
        if (filterBadge == null) {
            filterBadge = QBadgeView(context)
                .setBadgeGravity(Gravity.END or Gravity.TOP)
                .setBadgeNumber(number)
                .setBadgePadding(5f, true)
                .setBadgeTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .setBadgeBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
                .bindTarget(imageBtnFilter)
                .setOnDragStateChangedListener { dragState, _, _ ->
                    when (dragState) {
                        Badge.OnDragStateChangedListener.STATE_SUCCEED -> loadBikes(FilterModel.empty(), true)
                        Badge.OnDragStateChangedListener.STATE_CANCELED -> bikesViewModel.filterClick()
                    }
                }
        } else {
            filterBadge!!.setBadgeNumber(number).bindTarget(imageBtnFilter)
        }
    }

    private fun showProgress() {
        hideErrors()
        progressBarGlobal.visible()
    }

    private fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
        progressBarGlobal.gone()
        toolbarSubtitle.gone()
        hideErrors()
    }

    private fun hideErrors() {
        layoutConnectionError.gone()
        layoutServerError.gone()
        layoutNothingFoundFilter.gone()
    }
}