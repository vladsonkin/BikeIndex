package com.sonkins.bikeindex

import com.sonkins.bikeindex.features.about.AboutFragment
import com.sonkins.bikeindex.features.bike.BikeFragment
import com.sonkins.bikeindex.features.bike.BikeFragmentModule
import com.sonkins.bikeindex.features.bikes.BikesFragment
import com.sonkins.bikeindex.features.bikes.BikesFragmentModule
import com.sonkins.bikeindex.features.bikes.filter.FilterFragment
import com.sonkins.bikeindex.features.bikes.filter.FilterFragmentModule
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorsFragment
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorsFragmentModule
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturersFragmentModule
import com.sonkins.bikeindex.features.favorites.FavoritesFragment
import com.sonkins.bikeindex.features.favorites.FavoritesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
interface MainActivityModule {

    @ContributesAndroidInjector()
    fun contributeAboutFragment(): AboutFragment

    @ContributesAndroidInjector(modules = [BikeFragmentModule::class])
    fun contributeBikeFragment(): BikeFragment

    @ContributesAndroidInjector(modules = [BikesFragmentModule::class])
    fun contributeBikesFragment(): BikesFragment

    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    fun contributeFilterFragment(): FilterFragment

    @ContributesAndroidInjector(modules = [ColorsFragmentModule::class])
    fun contributeColorsFragment(): ColorsFragment

    @ContributesAndroidInjector(modules = [ManufacturersFragmentModule::class])
    fun contributeManufacturersFragment(): ManufacturersFragment
}