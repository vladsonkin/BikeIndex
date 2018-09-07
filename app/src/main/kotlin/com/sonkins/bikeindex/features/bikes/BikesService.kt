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

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BikesService @Inject constructor(retrofit: Retrofit) : BikesApi {
    private val bikesApi by lazy { retrofit.create(BikesApi::class.java) }

    override fun getBikes(
        serial: String?,
        manufacturer: String?,
        color: String?,
        type: String?,
        page: Int,
        perPage: Int
    ) = bikesApi.getBikes(serial, manufacturer, color, type, page, perPage)
}