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

package com.sonkins.bikeindex.core.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.sonkins.bikeindex.features.bike.Bike
import com.sonkins.bikeindex.features.favorites.FavoriteBikes

@Entity(tableName = "favorite_bike")
data class FavoriteBikeEntity(
    @PrimaryKey var id: Int,
    var title: String?,
    var serial: String?,
    var manufacturerName: String?,
    var year: Int?,
    var frameColors: String?,
    var largeImg: String?,
    var stolen: Boolean?,
    var stolenLocation: String?,
    var dateStolen: Int?,
    var registrationCreatedAt: Long?,
    var registrationUpdatedAt: Long?,
    var description: String?,
    var theftDescription: String?,
    var isFavorite: Boolean
) {
    constructor() : this(0, null, null, null, null, null, null, null, null, null, null, null, null, null, true)

    @Ignore
    constructor(bike: Bike) : this(
        bike.id,
        bike.title,
        bike.serial,
        bike.manufacturerName,
        bike.year,
        bike.frameColors,
        bike.largeImg,
        bike.stolen,
        bike.stolenLocation,
        bike.dateStolen,
        bike.registrationCreatedAt,
        bike.registrationUpdatedAt,
        bike.description,
        bike.theftDescription,
        bike.isFavorite
    )

    @Ignore
    fun toBike() = FavoriteBikes.Bike(
        id,
        title,
        serial,
        manufacturerName,
        year,
        frameColors,
        largeImg,
        stolen,
        stolenLocation,
        dateStolen,
        registrationCreatedAt,
        registrationUpdatedAt,
        description,
        theftDescription,
        isFavorite
    )
}