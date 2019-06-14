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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.platform.Event
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorModel
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturerModel
import javax.inject.Inject

class FilterViewModel @Inject constructor() : ViewModel() {

    private var _sharedFilter = MutableLiveData<Event<FilterModel>>()
    val sharedFilter: LiveData<Event<FilterModel>>
        get() = _sharedFilter

    private var _applyFilterEvent = MutableLiveData<Event<Any>>()
    val applyFilterEvent: LiveData<Event<Any>>
        get() = _applyFilterEvent

    private var _filterEvent = MutableLiveData<Event<FilterModel>>()
    val filterEvent: LiveData<Event<FilterModel>>
        get() = _filterEvent

    private var filter = FilterModel.empty()

    fun setColor(colorModel: ColorModel) {
        filter.colorModel = colorModel
        this._filterEvent.value = Event(filter)
    }

    fun setManufacturer(manufacturerModel: ManufacturerModel) {
        filter.manufacturerModel = manufacturerModel
        this._filterEvent.value = Event(filter)
    }

    fun setType(type: FilterModel.Type) {
        filter.type = type
        this._filterEvent.value = Event(filter)
    }

    fun setSerial(serial: String) {
        filter.serial = serial
    }

    fun setStolenLocation(stolenLocation: String) {
        filter.stolenLocation = stolenLocation
    }

    fun setFilter(filterModel: FilterModel) {
        this.filter = filterModel.copy()
        this._filterEvent.value = Event(filter)
    }

    fun hasActiveFilters() = filter.countActiveFilters() != 0

    fun applyFilter() {
        filter.page = 1
        this._sharedFilter.value = Event(filter)
        this._applyFilterEvent.value = Event(Any())
    }

    fun clearFilter() {
        this.filter = FilterModel.empty()
        this._filterEvent.value = Event(filter)
    }
}