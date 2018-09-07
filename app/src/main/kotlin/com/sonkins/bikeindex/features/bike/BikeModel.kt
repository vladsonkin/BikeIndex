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

import android.os.Parcelable
import com.sonkins.bikeindex.core.extension.toStringDate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BikeModel(
    val id: Int,
    val title: String,
    val serial: String,
    val manufacturerName: String,
    val year: Int?,
    val yearString: String,
    val frameColors: String,
    val largeImg: String?,
    val stolen: Boolean?,
    val stolenLocation: String,
    val dateStolen: Int?,
    val registrationCreatedAt: Long?,
    val registrationUpdatedAt: Long?,
    val description: String,
    val theftDescription: String,
    var isFavorite: Boolean,
    var stolenInfo: String,
    var createdInfo: String,
    var updatedInfo: String
) : Parcelable {

    constructor(bike: Bike) : this(
        bike.id,
        bike.title ?: "Unknown",
        bike.serial ?: "Unknown",
        bike.manufacturerName ?: "Unknown",
        bike.year,
        bike.year?.toString() ?: "Unknown",
        bike.frameColors ?: "Unknown",
        bike.largeImg,
        bike.stolen,
        bike.stolenLocation ?: "Unknown",
        bike.dateStolen,
        bike.registrationCreatedAt,
        bike.registrationUpdatedAt,
        bike.description.takeUnless { it.isNullOrEmpty() } ?: "No description",
        bike.theftDescription.takeUnless { it.isNullOrEmpty() } ?: "No description",
        bike.isFavorite,
        "Stolen ${bike.dateStolen?.let { String.toStringDate(it) } ?: ""}",
        "Registered ${bike.registrationCreatedAt?.let { String.toStringDate(it) } ?: "Unknown"}",
        "Info updated ${bike.registrationUpdatedAt?.let { String.toStringDate(it) } ?: "Unknown"}"
    )

    fun toBike() = Bike(
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