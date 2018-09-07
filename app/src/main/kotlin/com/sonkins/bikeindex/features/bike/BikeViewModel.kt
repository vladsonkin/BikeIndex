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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.core.extension.launchAsync
import com.sonkins.bikeindex.core.platform.DataState
import com.sonkins.bikeindex.core.platform.Event
import javax.inject.Inject

class BikeViewModel @Inject constructor(
    private val getBikeUseCase: GetBikeUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase
) : ViewModel() {

    private val _bikeDataStateEvent = MutableLiveData<Event<DataState<BikeModel>>>()
    val bikeDataStateEvent: LiveData<Event<DataState<BikeModel>>>
        get() = _bikeDataStateEvent

    private var _favoriteEvent = MutableLiveData<Event<DataState<Boolean>>>()
    val favoriteEvent: LiveData<Event<DataState<Boolean>>>
        get() = _favoriteEvent

    fun loadBike(id: Int) {
        launchAsync {
            try {
                val bikeModel = asyncAwait { BikeModel(getBikeUseCase.run(id)) }
                _bikeDataStateEvent.value = Event(DataState.Success(bikeModel))
            } catch (exception: Exception) {
                _bikeDataStateEvent.value = Event(DataState.Error(exception, obtainCurrentData()))
            }
        }
    }

    fun favoriteClick() {
        launchAsync {
            try {
                obtainCurrentData()?.let {
                    if (isFavorite()) {
                        it.isFavorite = false
                        asyncAwait { removeFromFavoriteUseCase.run(it.toBike()) }
                    } else {
                        it.isFavorite = true
                        asyncAwait { addToFavoriteUseCase.run(it.toBike()) }
                    }
                    _favoriteEvent.value = Event(DataState.Success(it.isFavorite))
                }
            } catch (exception: Exception) {
                _favoriteEvent.value = Event(DataState.Error(exception, false))
            }
        }
    }

    fun obtainCurrentData() = _bikeDataStateEvent.value?.peekContent()?.data

    fun isFavorite() = obtainCurrentData()?.isFavorite == true
}