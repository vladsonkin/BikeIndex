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
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.sonkins.bikeindex.BikeIndexApplication
import com.sonkins.bikeindex.BuildConfig
import com.sonkins.bikeindex.core.db.BikeIndexDatabase
import com.sonkins.bikeindex.core.db.FavoriteBikeDao
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideApplicationContext(app: BikeIndexApplication): Context = app

    @Provides
    @Singleton
    fun provideBikeIndexDatabase(context: Context): BikeIndexDatabase {
        return Room.databaseBuilder(context, BikeIndexDatabase::class.java, BikeIndexDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteBikeDao(db: BikeIndexDatabase): FavoriteBikeDao {
        return db.favoriteBikeDao()
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
}