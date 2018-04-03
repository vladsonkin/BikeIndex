package com.sonkins.bikeindex.presentation.di.module

import com.google.gson.Gson
import com.sonkins.bikeindex.data.api.BikeIndexApiService
import com.sonkins.bikeindex.data.executor.JobExecutor
import com.sonkins.bikeindex.data.repository.BikeDataRepository
import com.sonkins.bikeindex.domain.executor.PostExecutionThread
import com.sonkins.bikeindex.domain.executor.ThreadExecutor
import com.sonkins.bikeindex.domain.repository.BikeRepository
import com.sonkins.bikeindex.presentation.UiThread
import com.sonkins.bikeindex.presentation.di.ApplicationScope
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
    @ApplicationScope
    fun provideGson(): Gson = Gson()

    @Provides
    @ApplicationScope
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @ApplicationScope
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @ApplicationScope
    internal fun provideBikeRepository(bikeDataRepository: BikeDataRepository): BikeRepository
            = bikeDataRepository

    @Provides
    @ApplicationScope
    internal fun provideBikeApiService(
            @Named("client") bikeIndexApiService: BikeIndexApiService): BikeIndexApiService
            = bikeIndexApiService

}