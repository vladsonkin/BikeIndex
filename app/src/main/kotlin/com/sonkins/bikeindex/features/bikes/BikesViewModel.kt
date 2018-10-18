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

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.platform.DataState
import com.sonkins.bikeindex.core.platform.Event
import com.sonkins.bikeindex.features.bikes.filter.FilterModel
import javax.inject.Inject

class BikesViewModel @Inject constructor(private val getBikesUseCase: GetBikesUseCase) : ViewModel() {

    private val _bikesDataState = MutableLiveData<DataState<BikesModel>>()
    val bikesDataState: LiveData<DataState<BikesModel>>
        get() = _bikesDataState
    var currentBikeScreenPosition = 0

    private val _navigateToFilterEvent = MutableLiveData<Event<Bundle>>()
    val navigateToFilterEvent: LiveData<Event<Bundle>>
        get() = _navigateToFilterEvent

    private var filterModel = FilterModel.empty()
    var refresh = false

    fun refreshBikes() {
        filterModel.page = 1
        loadBikes(filterModel, true)
    }

    fun loadBikes(filter: FilterModel, refresh: Boolean) {
        this.filterModel = filter
        this.refresh = refresh

        launchAsync {
            try {
                val bikesModel = asyncAwait {
                    BikesModel(
                        getBikesUseCase.run(
                            filter.serial,
                            filter.manufacturerModel?.name,
                            filter.colorModel?.name,
                            filter.type,
                            filter.page,
                            filter.perPage
                        )
                    )
                }

                // add to bikes only if its not refreshing and it's not first page
                if (!refresh && filter.page != 1) {
                    val currentBikeModels = obtainCurrentData()?.bikeModels?.toMutableList()
                    currentBikeModels?.addAll(bikesModel.bikeModels)

                    currentBikeModels?.let {
                        bikesModel.bikeModels = it
                    }
                }

                _bikesDataState.value = DataState.Success(bikesModel)
            } catch (exception: Exception) {
                _bikesDataState.value = DataState.Error(exception, obtainCurrentData())
            }
        }
    }

    fun loadMoreBikes() {
        filterModel.page += 1
        loadBikes(filterModel, false)
    }

    fun filterClick() {
        val bundle = bundleOf("filterModel" to filterModel)
        _navigateToFilterEvent.value = Event(bundle)
    }

    fun obtainCurrentData() = _bikesDataState.value?.data

    fun countActiveFilters() = filterModel.countActiveFilters()
}