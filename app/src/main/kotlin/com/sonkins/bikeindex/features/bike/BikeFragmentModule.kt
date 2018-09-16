package com.sonkins.bikeindex.features.bike

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class BikeFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBikeRepository(bikeRepository: BikeRepository.BikeDataRepository): BikeRepository = bikeRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(BikeViewModel::class)
    abstract fun bindBikeViewModel(bikeViewModel: BikeViewModel): ViewModel
}