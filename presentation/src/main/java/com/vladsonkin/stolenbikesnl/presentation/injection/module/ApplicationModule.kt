package com.vladsonkin.stolenbikesnl.presentation.injection.module

import com.vladsonkin.stolenbikesnl.data.api.BikeApiService
import com.vladsonkin.stolenbikesnl.data.executor.JobExecutor
import com.vladsonkin.stolenbikesnl.data.repository.BikeDataRepository
import com.vladsonkin.stolenbikesnl.domain.executor.PostExecutionThread
import com.vladsonkin.stolenbikesnl.domain.executor.ThreadExecutor
import com.vladsonkin.stolenbikesnl.domain.repository.BikeRepository
import com.vladsonkin.stolenbikesnl.presentation.UiThread
import com.vladsonkin.stolenbikesnl.presentation.injection.PerApplication
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