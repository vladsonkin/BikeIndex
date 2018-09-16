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

import com.sonkins.bikeindex.core.db.FavoriteBikeDao
import com.sonkins.bikeindex.core.db.FavoriteBikeEntity
import com.sonkins.bikeindex.core.exception.ServerException
import com.sonkins.bikeindex.core.platform.BaseNetwork
import javax.inject.Inject

interface BikeRepository {

    fun getBike(id: Int): Bike
    fun addToFavorites(bike: Bike): Long
    fun removeFromFavorites(bike: Bike): Int

    class BikeDataRepository @Inject constructor(
        private val networkDataStore: NetworkDataStore,
        private val databaseDataStore: DatabaseDataStore
    ) : BikeRepository {

        override fun getBike(id: Int): Bike {
            val favoriteBikeEntity = databaseDataStore.getFavorite(id)

            return networkDataStore.getBike(id).first?.toBike(favoriteBikeEntity != null)
                ?: throw ServerException("Empty bike body. Bike id = $id")
        }

        override fun addToFavorites(bike: Bike) = databaseDataStore.addToFavorites(FavoriteBikeEntity(bike))

        override fun removeFromFavorites(bike: Bike) = databaseDataStore.removeFromFavorites(FavoriteBikeEntity(bike))

        class DatabaseDataStore @Inject constructor(private val favoriteBikeDao: FavoriteBikeDao) {
            fun addToFavorites(favoriteBikeEntity: FavoriteBikeEntity) =
                favoriteBikeDao.addToFavorites(favoriteBikeEntity)

            fun removeFromFavorites(favoriteBikeEntity: FavoriteBikeEntity) =
                favoriteBikeDao.removeFromFavorites(favoriteBikeEntity)

            fun getFavorite(id: Int) = favoriteBikeDao.getFavorite(id)
        }

        class NetworkDataStore @Inject constructor(private val service: BikeService) : BaseNetwork() {
            fun getBike(id: Int) = request(service.getBike(id))
        }
    }
}