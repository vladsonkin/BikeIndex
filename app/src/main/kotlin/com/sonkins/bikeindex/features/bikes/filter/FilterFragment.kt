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
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.activityViewModel
import com.sonkins.bikeindex.core.extension.afterTextChanged
import com.sonkins.bikeindex.core.extension.hideKeyboard
import com.sonkins.bikeindex.core.extension.invisible
import com.sonkins.bikeindex.core.extension.navigate
import com.sonkins.bikeindex.core.extension.navigateUp
import com.sonkins.bikeindex.core.extension.observe
import com.sonkins.bikeindex.core.extension.showKeyboard
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.BaseFragment
import com.sonkins.bikeindex.core.platform.Event
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorModel
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturerModel
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : BaseFragment() {

    private lateinit var filterViewModel: FilterViewModel
    private var filterEventHasBeenHandled = false

    companion object {
        const val FILTER_EVENT_HANDLED = "filterEventHasBeenHandled"
    }

    override fun layoutId() = R.layout.fragment_filter

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putBoolean(FILTER_EVENT_HANDLED, filterEventHasBeenHandled) }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        filterEventHasBeenHandled = savedInstanceState?.getBoolean(FILTER_EVENT_HANDLED) ?: false

        filterViewModel = activityViewModel(viewModelFactory) {
            observe(filterEvent, ::handleFilterEvent)
            observe(applyFilterEvent) { event ->
                event?.getContentIfNotHandled { navigateUp() }
            }
        }
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

        btnClear.setOnClickListener {
            editSerial.clearFocus()
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

        radioGroupType.setOnCheckedChangeListener { radioGroup, checkedId ->
            editSerial.clearFocus()
            radioGroup.hideKeyboard()
            when (checkedId) {
                R.id.typeAll -> filterViewModel.setType(FilterModel.Type.ALL)
                R.id.typeStolen -> filterViewModel.setType(FilterModel.Type.STOLEN)
                R.id.typeNotStolen -> filterViewModel.setType(FilterModel.Type.NOT_STOLEN)
            }
        }
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
        renderManufacture(filterModel.manufacturerModel)
        renderType(filterModel.type)
    }

    private fun showOrHideClearButton() {
        if (filterViewModel.hasActiveFilters()) btnClear.visible() else btnClear.invisible()
    }

    private fun renderSerial(serial: String?) {
        editSerial.setText(serial)
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
        } ?: textViewColor.invisible()
    }

    private fun renderManufacture(manufacturerModel: ManufacturerModel?) {
        manufacturerModel?.let {
            textViewManufacture.visible()
            textViewManufacture.text = it.name
        } ?: textViewManufacture.invisible()
    }
}