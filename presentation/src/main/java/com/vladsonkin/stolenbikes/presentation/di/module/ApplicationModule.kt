package com.vladsonkin.stolenbikes.presentation.di.module

import com.vladsonkin.stolenbikes.data.api.BikeApiService
import com.vladsonkin.stolenbikes.data.executor.JobExecutor
import com.vladsonkin.stolenbikes.data.repository.BikeDataRepository
import com.vladsonkin.stolenbikes.domain.executor.PostExecutionThread
import com.vladsonkin.stolenbikes.domain.executor.ThreadExecutor
import com.vladsonkin.stolenbikes.domain.repository.BikeRepository
import com.vladsonkin.stolenbikes.presentation.UiThread
import com.vladsonkin.stolenbikes.presentation.di.PerApplication
import dagger.Module
import dagger.Provides

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module(includes = arrayOf(ApiServiceModule::class))
open class ApplicationModule {

    @Provides
    @PerApplication
    internal fun provideBikeRepository(bikeDataRepository: BikeDataRepository): BikeRepository =
            bikeDataRepository

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @PerApplication
    internal fun provideMainApiService(bikeApiService: BikeApiService): BikeApiService =
            bikeApiService

}