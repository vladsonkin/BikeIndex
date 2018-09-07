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

import com.sonkins.bikeindex.core.extension.asyncAwait
import com.sonkins.bikeindex.features.bikes.filter.FilterModel
import javax.inject.Inject

class GetBikesUseCase @Inject constructor(private val bikesRepository: BikesRepository) {

    suspend fun run(
        serial: String?,
        manufacturer: String?,
        color: String?,
        type: FilterModel.Type,
        page: Int,
        perPage: Int
    ): Bikes {
        return asyncAwait {
            val bikes = bikesRepository.getBikes(
                serial,
                manufacturer,
                color,
                type.value,
                page,
                perPage
            )

            if (bikes.bikes.isEmpty() && page == 1) {
                throw NothingFoundException("Bikes not found with this filter")
            } else {
                bikes
            }
        }
    }
}