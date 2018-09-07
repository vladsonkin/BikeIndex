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

package com.sonkins.bikeindex.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sonkins.bikeindex.features.bike.BikeViewModel
import com.sonkins.bikeindex.features.bikes.BikesViewModel
import com.sonkins.bikeindex.features.bikes.filter.FilterViewModel
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorsViewModel
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturersViewModel
import com.sonkins.bikeindex.features.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BikesViewModel::class)
    fun bindBikesViewModel(bikesViewModel: BikesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BikeViewModel::class)
    fun bindBikeViewModel(bikeViewModel: BikeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ColorsViewModel::class)
    fun bindColorViewModel(colorsViewModel: ColorsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturersViewModel::class)
    fun bindManufacturerViewModel(manufacturersViewModel: ManufacturersViewModel): ViewModel
}