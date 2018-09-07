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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.platform.DataState
import javax.inject.Inject

class ColorsViewModel @Inject constructor(private val getColorsUseCase: GetColorsUseCase) : ViewModel() {

    private val _colorsDataState = MutableLiveData<DataState<List<ColorModel>>>()
    val colorsDataState: LiveData<DataState<List<ColorModel>>>
        get() = _colorsDataState

    fun loadColors() {
        launchAsync {
            try {
                val colorModels = asyncAwait {
                    getColorsUseCase.run().map {
                        ColorModel(it.name, it.slug)
                    }
                }
                _colorsDataState.value = DataState.Success(colorModels)
            } catch (exception: Exception) {
                _colorsDataState.value = DataState.Error(exception, obtainCurrentData())
            }
        }
    }

    fun obtainCurrentData() = _colorsDataState.value?.data
}