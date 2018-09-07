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

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BikeService @Inject constructor(retrofit: Retrofit) : BikeApi {

    private val bikeApi by lazy { retrofit.create(BikeApi::class.java) }

    override fun getBike(id: Int) = bikeApi.getBike(id)
}