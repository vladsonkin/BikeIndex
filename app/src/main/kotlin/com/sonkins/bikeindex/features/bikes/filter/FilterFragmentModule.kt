package com.sonkins.bikeindex.features.bikes.filter

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class FilterFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideFilterRepository(filterRepository: FilterRepository.FilterDataRepository): FilterRepository =
            filterRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel
}