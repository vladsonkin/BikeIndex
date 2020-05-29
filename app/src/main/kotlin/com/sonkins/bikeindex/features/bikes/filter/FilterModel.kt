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

import android.os.Parcelable
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorModel
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturerModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterModel(
    var serial: String?,
    var colorModel: ColorModel?,
    var manufacturerModel: ManufacturerModel?,
    var type: Type,
    var stolenLocation: String?,
    var page: Int,
    var perPage: Int
) : Parcelable {

    companion object {
        fun empty() = FilterModel(
            null,
            null,
            null,
            Type.STOLEN,
                null,
            1,
            25
        )
    }

    fun countActiveFilters(): Int {
        var activeFilters = 0

        if (!serial.isNullOrEmpty()) {
            activeFilters++
        }

        if (colorModel != null) {
            activeFilters++
        }

        if (manufacturerModel != null) {
            activeFilters++
        }

        if (type == Type.ALL || type == Type.NOT_STOLEN) {
            activeFilters++
        }

        if (!stolenLocation.isNullOrEmpty() && type == Type.STOLEN) {
            activeFilters++
        }

        return activeFilters
    }

    enum class Type(val type: String, val value: String) {
        STOLEN("Stolen", "stolen"),
        NOT_STOLEN("Not stolen", "non"),
        ALL("All", "all"),
        PROXIMITY("Proximity", "proximity")
    }
}