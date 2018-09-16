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

package com.sonkins.bikeindex.features.favorites

import com.sonkins.bikeindex.core.db.FavoriteBikeDao
import javax.inject.Inject

interface FavoritesRepository {

    fun getFavoriteBikes(): List<FavoriteBikes.Bike>
    fun getFavoriteBikesCount(): Int

    class FavoritesDataRepository @Inject constructor(private val databaseDataStore: DatabaseDataStore) :
        FavoritesRepository {

        override fun getFavoriteBikes() = databaseDataStore.getFavoriteBikes().map { it.toBike() }

        override fun getFavoriteBikesCount() = databaseDataStore.getFavoriteBikesCount()

        class DatabaseDataStore @Inject constructor(private val favoriteBikeDao: FavoriteBikeDao) {
            fun getFavoriteBikes() = favoriteBikeDao.getFavorites()
            fun getFavoriteBikesCount() = favoriteBikeDao.getFavoritesCount()
        }
    }
}