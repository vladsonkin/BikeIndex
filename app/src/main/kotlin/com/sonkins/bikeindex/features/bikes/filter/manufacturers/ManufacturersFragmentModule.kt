package com.sonkins.bikeindex.features.bikes.filter.manufacturers

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import com.sonkins.bikeindex.features.bikes.filter.FilterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ManufacturersFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideFilterRepository(filterRepository: FilterRepository.FilterDataRepository): FilterRepository =
            filterRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturersViewModel::class)
    abstract fun bindManufacturerViewModel(manufacturersViewModel: ManufacturersViewModel): ViewModel
}