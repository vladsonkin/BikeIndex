package com.sonkins.bikeindex.presentation.di.module

import com.sonkins.bikeindex.data.api.BikeIndexApiService
import com.sonkins.bikeindex.data.repository.BikeDataRepository
import com.sonkins.bikeindex.domain.repository.BikeRepository
import com.sonkins.bikeindex.presentation.di.ApplicationScope
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module(includes = [(ApiServiceModule::class)])
interface ApplicationModule {

    @Binds
    @ApplicationScope
    fun provideBikeRepository(bikeDataRepository: BikeDataRepository): BikeRepository

    @Binds
    @ApplicationScope
    fun provideBikeApiService(@Named("client") bikeIndexApiService: BikeIndexApiService): BikeIndexApiService

}