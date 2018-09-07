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

package com.sonkins.bikeindex.features.bikes.filter.manufacturers

import com.google.gson.annotations.SerializedName

data class ManufacturersResponse(
    @SerializedName("manufacturers")
    private val manufacturersResponse: List<ManufacturerResponse>
) {

    fun toManufacturers() = manufacturersResponse.map { it.toManufacturer() }

    data class ManufacturerResponse(
        @SerializedName("id") private val id: Int,
        @SerializedName("name") private val name: String,
        @SerializedName("company_url") private val companyUrl: String?,
        @SerializedName("frame_maker") private val frameMaker: Boolean?,
        @SerializedName("image") private val image: String?,
        @SerializedName("description") private val description: String?,
        @SerializedName("slug") private val slug: String?
    ) {

        fun toManufacturer() = Manufacturer(id, name, companyUrl, frameMaker, image, description, slug)
    }
}