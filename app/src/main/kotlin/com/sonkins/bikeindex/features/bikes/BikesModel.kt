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

import android.os.Parcelable
import com.sonkins.bikeindex.core.extension.makeStolenInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BikesModel(
    val total: Int, var bikeModels: List<BikeModel>
) : Parcelable {

    constructor(bikes: Bikes) : this(
        bikes.total,
        bikes.bikes.map { BikeModel(it) }
    )

    @Parcelize
    data class BikeModel(
        val id: Int,
        val title: String,
        val serial: String,
        val manufacturerName: String,
        val year: Int?,
        val frameColors: String,
        val largeImg: String?,
        val stolen: Boolean?,
        val stolenLocation: String?,
        val dateStolen: Int?,
        val stolenInfo: String?
    ) : Parcelable {

        constructor(bike: Bikes.Bike) : this(
            bike.id, bike.title ?: "Unknown", bike.serial ?: "Unknown",
            bike.manufacturerName ?: "Unknown", bike.year,
            bike.frameColors ?: "Unknown", bike.largeImg, bike.stolen,
            bike.stolenLocation, bike.dateStolen,
            if (bike.stolen == true) {
                String.makeStolenInfo(bike.dateStolen, bike.stolenLocation)
            } else null
        )
    }
}

