package com.sonkins.bikeindex.features.favorites

import androidx.lifecycle.ViewModel
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class FavoritesFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideFavoritesRepository(favoritesRepository: FavoritesRepository.FavoritesDataRepository): FavoritesRepository =
            favoritesRepository
    }

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel
}