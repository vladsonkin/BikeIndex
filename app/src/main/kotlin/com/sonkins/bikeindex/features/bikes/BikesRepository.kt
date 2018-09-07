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

import com.sonkins.bikeindex.core.platform.BaseNetwork
import javax.inject.Inject

interface BikesRepository {

    fun getBikes(serial: String?, manufacturer: String?, color: String?, type: String?, page: Int, perPage: Int): Bikes

    class BikesDataRepository @Inject constructor(private val networkDataStore: NetworkDataStore) : BikesRepository {

        override fun getBikes(
            serial: String?,
            manufacturer: String?,
            color: String?,
            type: String?,
            page: Int,
            perPage: Int
        ): Bikes {
            val (bikesResponse, headers) = networkDataStore.getBikes(serial, manufacturer, color, type, page, perPage)
            return bikesResponse?.toBikes(headers.get("Total")?.toInt() ?: 0) ?: Bikes.empty()
        }

        class NetworkDataStore @Inject constructor(private val service: BikesService) : BaseNetwork() {
            fun getBikes(
                serial: String?,
                manufacturer: String?,
                color: String?,
                type: String?,
                page: Int,
                perPage: Int
            ) = request(service.getBikes(serial, manufacturer, color, type, page, perPage))
        }
    }
}