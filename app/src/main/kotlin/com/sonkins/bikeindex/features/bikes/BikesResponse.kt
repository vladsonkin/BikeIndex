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

import com.google.gson.annotations.SerializedName

data class BikesResponse(@SerializedName("bikes") private val bikesResponse: List<BikeResponse>) {

    fun toBikes(total: Int) = Bikes(total, bikesResponse.map { it.toBike() })

    data class BikeResponse(
        @SerializedName("id") private val id: Int,
        @SerializedName("title") private val title: String?,
        @SerializedName("serial") private val serial: String?,
        @SerializedName("manufacturer_name") private val manufacturerName: String?,
        @SerializedName("year") private val year: Int?,
        @SerializedName("frame_colors") private val frameColors: List<String>?,
        @SerializedName("large_img") private val largeImg: String?,
        @SerializedName("stolen") private val stolen: Boolean?,
        @SerializedName("stolen_location") private val stolenLocation: String?,
        @SerializedName("date_stolen") private val dateStolen: Int
    ) {

        fun toBike() = Bikes.Bike(
            id,
            title,
            serial,
            manufacturerName,
            year,
            frameColors?.joinToString(),
            largeImg,
            stolen,
            stolenLocation.takeIf { !it.isNullOrEmpty() }?.split(",")?.joinToString(),
            dateStolen
        )
    }
}