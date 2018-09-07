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

import com.sonkins.bikeindex.core.platform.BaseNetwork
import com.sonkins.bikeindex.features.bikes.filter.colors.Color
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.Manufacturer
import javax.inject.Inject

interface FilterRepository {

    fun getColors(): List<Color>
    fun getManufacturers(page: Int, perPage: Int): List<Manufacturer>

    class FilterDataRepository @Inject constructor(private val networkDataStore: NetworkDataStore) : FilterRepository {

        override fun getManufacturers(page: Int, perPage: Int) =
            networkDataStore.getManufacturers(page, perPage).first?.toManufacturers() ?: emptyList()

        override fun getColors() = networkDataStore.getColors().first?.toColors() ?: emptyList()

        class NetworkDataStore @Inject constructor(private val service: FilterService) : BaseNetwork() {
            fun getManufacturers(page: Int, perPage: Int) = request(service.getManufacturers(page, perPage))
            fun getColors() = request(service.getColors())
        }
    }
}