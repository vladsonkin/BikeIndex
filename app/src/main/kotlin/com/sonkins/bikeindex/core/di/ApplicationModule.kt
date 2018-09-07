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

package com.sonkins.bikeindex.core.di

import android.content.Context
import androidx.room.Room
import com.sonkins.bikeindex.AndroidApplication
import com.sonkins.bikeindex.BuildConfig
import com.sonkins.bikeindex.core.db.BikeIndexDatabase
import com.sonkins.bikeindex.features.bike.BikeRepository
import com.sonkins.bikeindex.features.bikes.BikesRepository
import com.sonkins.bikeindex.features.bikes.filter.FilterRepository
import com.sonkins.bikeindex.features.favorites.FavoritesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideBikeIndexDatabase(context: Context): BikeIndexDatabase {
        return Room.databaseBuilder(context, BikeIndexDatabase::class.java, BikeIndexDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bikeindex.org:443/api/v3/")
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideBikesRepository(bikesRepository: BikesRepository.BikesDataRepository): BikesRepository = bikesRepository

    @Provides
    @Singleton
    fun provideBikeRepository(bikeRepository: BikeRepository.BikeDataRepository): BikeRepository = bikeRepository

    @Provides
    @Singleton
    fun provideFilterRepository(filterRepository: FilterRepository.FilterDataRepository): FilterRepository =
        filterRepository

    @Provides
    @Singleton
    fun provideFavoritesRepository(favoritesRepository: FavoritesRepository.FavoritesDataRepository): FavoritesRepository =
        favoritesRepository
}