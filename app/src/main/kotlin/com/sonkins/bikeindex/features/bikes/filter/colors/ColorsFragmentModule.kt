package com.sonkins.bikeindex.features.bikes.filter.colors

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import com.sonkins.bikeindex.features.bikes.filter.FilterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ColorsFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideFilterRepository(filterRepository: FilterRepository.FilterDataRepository): FilterRepository =
            filterRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(ColorsViewModel::class)
    abstract fun bindColorViewModel(colorsViewModel: ColorsViewModel): ViewModel
}