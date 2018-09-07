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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.platform.DataState
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val getFavoriteBikesUseCase: GetFavoriteBikesUseCase) :
    ViewModel() {

    private val _favoritesDataState = MutableLiveData<DataState<FavoriteBikesModel>>()
    val favoritesDataState: LiveData<DataState<FavoriteBikesModel>>
        get() = _favoritesDataState

    fun loadFavoriteBikes() {
        launchAsync {
            try {
                val favoriteBikesModel = asyncAwait { FavoriteBikesModel(getFavoriteBikesUseCase.run()) }
                _favoritesDataState.value = DataState.Success(favoriteBikesModel)
            } catch (exception: Exception) {
                _favoritesDataState.value = DataState.Error(exception, obtainCurrentData())
            }
        }
    }

    private fun obtainCurrentData() = _favoritesDataState.value?.data
}