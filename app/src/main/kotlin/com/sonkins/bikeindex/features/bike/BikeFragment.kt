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

package com.sonkins.bikeindex.features.bike

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.sonkins.bikeindex.MainActivity
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.exception.ConnectionException
import com.sonkins.bikeindex.core.extension.gone
import com.sonkins.bikeindex.core.extension.loadFromUrl
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.snack
import com.sonkins.bikeindex.core.extension.viewModel
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.DataState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_bike.*
import kotlinx.android.synthetic.main.view_error_bike_registration.*
import kotlinx.android.synthetic.main.view_error_connection.*
import kotlinx.android.synthetic.main.view_error_server.*
import kotlinx.android.synthetic.main.view_progress.*
import javax.inject.Inject

class BikeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var bikeViewModel: BikeViewModel
    private var favoriteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        bikeViewModel = viewModel(viewModelFactory) {
            observe(bikeDataStateEvent) {
                it?.getContentIfNotHandled { dataState ->
                    handleDataState(dataState)
                }
            }

            observe(favoriteEvent) {
                it?.getContentIfNotHandled { dataState ->
                    handleFavoriteEvent(dataState)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bike, menu)
        favoriteMenuItem = menu.findItem(R.id.menu_favorite)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        favoriteMenuItem?.let {
            updateFavoriteMenuItemColor(it, bikeViewModel.isFavorite())
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.also { _ ->
            when (item.itemId) {
                R.id.menu_favorite -> bikeViewModel.favoriteClick()
                R.id.menu_share -> {
                    bikeViewModel.obtainCurrentData()?.let {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, it.id))
                        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)))
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bike, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        with(BikeFragmentArgs.fromBundle(arguments).bikeId) {
            buttonRegisterOnWebsite.setOnClickListener { forceOpenInBrowser(getString(R.string.bike_registration_link)) }
            buttonConnectionTryAgain.setOnClickListener { loadBike(this) }
            buttonServerTryAgain.setOnClickListener { loadBike(this) }

            bikeViewModel.obtainCurrentData()?.let {
                renderBike(it)
            } ?: loadBike(this)
        }
    }

    private fun initializeView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setPadding(0, (activity as MainActivity).getStatusBarHeight(), 0, 0)
        toolbar.layoutParams.height += (activity as MainActivity).getStatusBarHeight()
    }

    private fun loadBike(bikeId: String) {
        showProgress()
        bikeViewModel.loadBike(bikeId)
    }

    private fun handleDataState(bikeDataState: DataState<BikeModel>?) {
        hideProgress()

        when (bikeDataState) {
            is DataState.Success -> renderBike(bikeDataState.data)
            is DataState.Error -> handleError(bikeDataState.exception)
        }
    }

    private fun renderBike(bikeModel: BikeModel) {
        appbar.visible()
        layoutBike.visible()

        updateFavoriteMenuItemColor(favoriteMenuItem, bikeModel.isFavorite)
        (backdrop as ImageView).loadFromUrl(bikeModel.largeImg, R.drawable.ic_bike_220)
        collapsing_toolbar.title = bikeModel.title
        textViewBikeName.text = bikeModel.title
        textViewRegistered.text = bikeModel.createdInfo

        if (bikeModel.stolen == true) {
            layoutStolen.visible()
            textViewStolenTitle.text = bikeModel.stolenInfo
            textViewStolenLocation.text = bikeModel.stolenLocation
            textViewStolenDescription.text = bikeModel.theftDescription
            btnContactOwner.setOnClickListener { forceOpenInBrowser(getString(R.string.bike_link, bikeModel.id)) }
        } else {
            layoutStolen.gone()
        }

        textViewDescription.text = bikeModel.description
        textViewSerial.text = bikeModel.serial
        textViewManufacturer.text = bikeModel.manufacturerName
        textViewColor.text = bikeModel.frameColors
        textViewYear.text = bikeModel.yearString
        textViewUpdated.text = bikeModel.updatedInfo
    }

    private fun handleError(exception: Exception) {
        when (exception) {
            is ConnectionException -> layoutConnectionError.visible()
            is BikeRegistrationErrorException -> layoutBikeRegistrationError.visible()
            else -> layoutServerError.visible()
        }

        Crashlytics.logException(exception)
    }

    private fun handleFavoriteEvent(favoriteDataState: DataState<Boolean>?) {

        when (favoriteDataState) {
            is DataState.Success -> updateFavoriteMenuItemColor(favoriteMenuItem, bikeViewModel.isFavorite())
            is DataState.Error -> {
                Crashlytics.logException(favoriteDataState.exception)
                snack(messageRes = R.string.something_went_wrong, length = Snackbar.LENGTH_LONG) {}
            }
        }
    }

    private fun updateFavoriteMenuItemColor(item: MenuItem?, isFavorite: Boolean) {
        item?.let {
            if (isFavorite) {
                it.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_full_24, null)
            } else {
                it.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24, null)
            }
        }
    }

    /**
     * Force open url in browser with ignoring our app
     */
    private fun forceOpenInBrowser(url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)

        // Get all intents that can handle our intent with ignoring our application
        val packageManager = requireActivity().packageManager
        val activities = packageManager.queryIntentActivities(intent, 0)
        val packageNameToHide = context?.packageName
        var targetIntents = ArrayList<Intent>()
        for (currentInfo in activities) {
            val packageName = currentInfo.activityInfo.packageName
            if (packageNameToHide != packageName) {
                val targetIntent = Intent(Intent.ACTION_VIEW)
                targetIntent.data = Uri.parse(url)
                targetIntent.setPackage(packageName)
                targetIntents.add(targetIntent)
            }
        }
        // Remove possible duplicate intents
        targetIntents = (targetIntents.distinctBy { it.`package` }) as ArrayList<Intent>

        if (targetIntents.size > 0) {
            val chooserIntent = Intent.createChooser(targetIntents.removeAt(0), getString(R.string.contact_owner_title))
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetIntents.toArray(arrayOf<Parcelable>()))
            startActivity(chooserIntent)
        } else {
            Crashlytics.logException(IllegalArgumentException(getString(R.string.no_app_found_contact_owner)))
            snack(messageRes = R.string.something_went_wrong, length = Snackbar.LENGTH_LONG) {}
        }
    }

    private fun showProgress() {
        appbar.gone()
        layoutBike.gone()
        hideErrors()
        progressBarGlobal.visible()
    }

    private fun hideProgress() {
        progressBarGlobal.gone()
        hideErrors()
    }

    private fun hideErrors() {
        layoutBikeRegistrationError.gone()
        layoutConnectionError.gone()
        layoutServerError.gone()
    }
}