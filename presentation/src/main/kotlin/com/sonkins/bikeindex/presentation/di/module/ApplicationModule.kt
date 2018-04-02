package com.sonkins.bikeindex.presentation.di.module

import com.google.gson.Gson
import com.sonkins.bikeindex.data.api.BikeIndexApiService
import com.sonkins.bikeindex.data.executor.JobExecutor
import com.sonkins.bikeindex.data.repository.BikeDataRepository
import com.sonkins.bikeindex.domain.executor.PostExecutionThread
import com.sonkins.bikeindex.domain.executor.ThreadExecutor
import com.sonkins.bikeindex.domain.repository.BikeRepository
import com.sonkins.bikeindex.presentation.UiThread
import com.sonkins.bikeindex.presentation.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module(includes = arrayOf(ApiServiceModule::class))
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideGson(): Gson = Gson()

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @PerApplication
    internal fun provideBikeRepository(bikeDataRepository: BikeDataRepository): BikeRepository =
            bikeDataRepository

    @Provides
    @PerApplication
    internal fun provideBikeApiService(@Named("client") bikeIndexApiService: BikeIndexApiService): BikeIndexApiService =
            bikeIndexApiService

}