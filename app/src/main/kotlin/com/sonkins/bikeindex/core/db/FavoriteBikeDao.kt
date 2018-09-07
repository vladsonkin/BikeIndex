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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteBikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(favoriteBikeEntity: FavoriteBikeEntity): Long

    @Delete
    fun removeFromFavorites(favoriteBikeEntity: FavoriteBikeEntity): Int

    @Query("SELECT * from favorite_bike")
    fun getFavorites(): List<FavoriteBikeEntity>

    @Query("SELECT * from favorite_bike where id = :id")
    fun getFavorite(id: Int): FavoriteBikeEntity?

    @Query("SELECT COUNT(*) FROM favorite_bike")
    fun getFavoritesCount(): Int
}