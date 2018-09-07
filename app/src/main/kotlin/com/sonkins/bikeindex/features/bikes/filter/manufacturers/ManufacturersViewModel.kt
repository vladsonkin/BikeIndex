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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.platform.DataState
import javax.inject.Inject

class ManufacturersViewModel @Inject constructor(private val getManufacturersUseCase: GetManufacturersUseCase) :
    ViewModel() {

    private val _manufacturersDataState = MutableLiveData<DataState<List<ManufacturerModel>>>()
    val manufacturersDataState: LiveData<DataState<List<ManufacturerModel>>>
        get() = _manufacturersDataState

    private var page = 1
    private val perPage = 100
    var refresh = false

    fun refreshManufacturers() {
        page = 1
        loadManufacturers(true)
    }

    fun loadManufacturers(refresh: Boolean) {
        this.refresh = refresh

        launchAsync {
            try {
                val manufacturerModels = asyncAwait {
                    getManufacturersUseCase.run(page, perPage).map {
                        ManufacturerModel(
                            it.id,
                            it.name,
                            it.companyUrl,
                            it.frameMaker,
                            it.image,
                            it.description,
                            it.slug
                        )
                    }
                }

                if (!refresh && page != 1) {
                    val currentManufacturerModels = obtainCurrentData()?.toMutableList()
                    currentManufacturerModels?.addAll(manufacturerModels)

                    _manufacturersDataState.value = DataState.Success(currentManufacturerModels ?: manufacturerModels)
                } else {
                    _manufacturersDataState.value = DataState.Success(manufacturerModels)
                }
            } catch (exception: Exception) {
                _manufacturersDataState.value = DataState.Error(exception, obtainCurrentData())
            }
        }
    }

    fun loadMoreManufacturers() {
        page += 1
        loadManufacturers(false)
    }

    fun obtainCurrentData() = _manufacturersDataState.value?.data
}