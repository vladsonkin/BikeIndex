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

package com.sonkins.bikeindex.features.bikes.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sonkins.bikeindex.MainActivity
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.*
import com.sonkins.bikeindex.core.platform.Event
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorModel
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturerModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_filter.*
import javax.inject.Inject

class FilterFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var filterViewModel: FilterViewModel
    private var filterEventHasBeenHandled = false

    companion object {
        const val FILTER_EVENT_HANDLED = "filterEventHasBeenHandled"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putBoolean(FILTER_EVENT_HANDLED, filterEventHasBeenHandled) }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filterEventHasBeenHandled = savedInstanceState?.getBoolean(FILTER_EVENT_HANDLED) ?: false

        filterViewModel = activityViewModel(viewModelFactory) {
            observe(filterEvent, ::handleFilterEvent)
            observe(applyFilterEvent) { event ->
                event?.getContentIfNotHandled { navigateUp() }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!filterEventHasBeenHandled) {
            val filterModel = arguments?.getParcelable("filterModel") ?: FilterModel.empty()
            filterViewModel.setFilter(filterModel)
        }

        initializeView()
    }

    private fun initializeView() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setPadding(0, (activity as MainActivity).getStatusBarHeight(), 0, 0)

        btnClear.setOnClickListener {
            editSerial.clearFocus()
            editStolenLocation.clearFocus()
            it.hideKeyboard()
            filterViewModel.clearFilter()
        }

        fabApply.setOnClickListener {
            it.hideKeyboard()
            filterViewModel.applyFilter()
        }

        filterSerial.setOnClickListener {
            editSerial.requestFocus()
            editSerial.showKeyboard()
        }

        filterColor.setOnClickListener {
            it.hideKeyboard()
            navigate(R.id.action_filterFragment_to_colorFragment)
        }

        filterManufacture.setOnClickListener {
            it.hideKeyboard()
            navigate(R.id.action_filterFragment_to_manufacturerFragment)
        }

        editSerial.afterTextChanged {
            filterViewModel.setSerial(it.trim())

            if (it.isNotEmpty()) {
                btnClear.visible()
            } else {
                showOrHideClearButton()
            }
        }

        editSerial.setOnEditorActionListener { textView, actionId, _ ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                editSerial.text?.toString()?.trim()?.let {
                    filterViewModel.setSerial(it)
                }
                editSerial.clearFocus()
                textView.hideKeyboard()
                handled = true
            }
            handled
        }

        editStolenLocation.afterTextChanged {
            filterViewModel.setStolenLocation(it.trim())

            if (it.isNotEmpty() && radioGroupType.checkedRadioButtonId == R.id.typeStolen) {
                btnClear.visible()
            } else {
                showOrHideClearButton()
            }
        }

        editStolenLocation.setOnEditorActionListener { textView, actionId, _ ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                editStolenLocation.text?.toString()?.trim()?.let {
                    filterViewModel.setStolenLocation(it)
                }
                editStolenLocation.clearFocus()
                textView.hideKeyboard()
                handled = true
            }
            handled
        }

        radioGroupType.setOnCheckedChangeListener { radioGroup, checkedId ->
            editSerial.clearFocus()
            radioGroup.hideKeyboard()
            when (checkedId) {
                R.id.typeAll -> {
                    locationType.gone()
                    filterViewModel.setType(FilterModel.Type.ALL)
                }
                R.id.typeStolen -> {
                    locationType.visible()
                    filterViewModel.setType(FilterModel.Type.STOLEN)
                }
                R.id.typeNotStolen -> {
                    locationType.gone()
                    filterViewModel.setType(FilterModel.Type.NOT_STOLEN)
                }
            }
        }

        imageViewLocationHelp.setOnClickListener { showStolenLocationHelp() }
    }

    private fun handleFilterEvent(filterEvent: Event<FilterModel>?) {
        if (filterEventHasBeenHandled && filterEvent?.hasBeenHandled == true) {
            filterEvent.peekContent { renderFilter(it) }
        } else {
            filterEvent?.getContentIfNotHandled {
                renderFilter(it)
                filterEventHasBeenHandled = true
            }
        }
    }

    private fun renderFilter(filterModel: FilterModel) {
        showOrHideClearButton()
        renderSerial(filterModel.serial)
        renderColor(filterModel.colorModel)
        renderManufacturer(filterModel.manufacturerModel)
        renderType(filterModel.type)
        renderStolenLocation(filterModel.stolenLocation)
    }

    private fun showOrHideClearButton() {
        if (filterViewModel.hasActiveFilters()) btnClear.visible() else btnClear.gone()
    }

    private fun renderSerial(serial: String?) {
        editSerial.setText(serial)
    }

    private fun renderStolenLocation(stolenLocation: String?) {
        editStolenLocation.setText(stolenLocation)
    }

    private fun renderType(type: FilterModel.Type) {
        when (type) {
            FilterModel.Type.ALL -> typeAll.isChecked = true
            FilterModel.Type.STOLEN -> typeStolen.isChecked = true
            FilterModel.Type.NOT_STOLEN -> typeNotStolen.isChecked = true
        }
    }

    private fun renderColor(colorModel: ColorModel?) {
        colorModel?.let {
            textViewColor.visible()
            textViewColor.text = it.name
        } ?: textViewColor.gone()
    }

    private fun renderManufacturer(manufacturerModel: ManufacturerModel?) {
        manufacturerModel?.let {
            textViewManufacture.visible()
            textViewManufacture.text = it.name
        } ?: textViewManufacture.gone()
    }

    private fun showStolenLocationHelp() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(getString(R.string.stolen_location_description))
                .setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.stolen_location_title))
        alert.show()
    }
}