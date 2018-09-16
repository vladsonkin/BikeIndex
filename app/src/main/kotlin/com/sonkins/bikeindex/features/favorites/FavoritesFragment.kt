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

package com.sonkins.bikeindex.features.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.gone
import com.sonkins.bikeindex.core.extension.navigate
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.viewModel
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.DataState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.view_empty_favorites.*
import kotlinx.android.synthetic.main.view_error_server.*
import kotlinx.android.synthetic.main.view_progress.*
import javax.inject.Inject

class FavoritesFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoritesViewModel = viewModel(viewModelFactory) {
            observe(favoritesDataState) {
                handleDataState(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadFavoriteBikes()
    }

    private fun initializeView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        favoritesAdapter.onFavoriteBikeClick = {
            navigate(FavoritesFragmentDirections.actionFavoritesFragmentToBikeFragment(it.id.toString()))
        }

        favoritesAdapter.onFavoriteBikeShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, it))
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)))
        }

        recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
        recyclerViewFavorites.adapter = favoritesAdapter
    }

    private fun loadFavoriteBikes() {
        showProgress()
        favoritesViewModel.loadFavoriteBikes()
    }

    private fun handleDataState(favoritesDataState: DataState<FavoriteBikesModel>?) {
        hideProgress()

        when (favoritesDataState) {
            is DataState.Success -> renderFavoriteBikes(favoritesDataState.data)
            is DataState.Error -> handleError(favoritesDataState.exception)
        }
    }

    private fun renderFavoriteBikes(favoriteBikesModel: FavoriteBikesModel) {
        favoritesAdapter.submitList(favoriteBikesModel.bikeModels)

        if (favoriteBikesModel.bikeModels.isEmpty()) {
            layoutEmptyFavorites.visible()
        } else {
            toolbarSubtitle.visible()
            toolbarSubtitle.text =
                resources.getQuantityString(R.plurals.bikes_count, favoriteBikesModel.total, favoriteBikesModel.total)
        }
    }

    private fun handleError(exception: Exception) {
        layoutServerError.visible()
        Crashlytics.logException(exception)
    }

    private fun showProgress() {
        hideErrors()
        progressBarGlobal.visible()
    }

    private fun hideProgress() {
        progressBarGlobal.gone()
        toolbarSubtitle.gone()
        hideErrors()
    }

    private fun hideErrors() {
        layoutEmptyFavorites.gone()
        layoutServerError.gone()
    }
}