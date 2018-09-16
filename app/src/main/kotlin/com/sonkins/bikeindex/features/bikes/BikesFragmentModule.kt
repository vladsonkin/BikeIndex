package com.sonkins.bikeindex.features.bikes

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import com.sonkins.bikeindex.features.bikes.filter.FilterViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class BikesFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBikesRepository(bikesRepository: BikesRepository.BikesDataRepository): BikesRepository =
            bikesRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(BikesViewModel::class)
    abstract fun bindBikesViewModel(bikesViewModel: BikesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel
}