/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.core.di

import com.sonkins.bikeindex.AndroidApplication
import com.sonkins.bikeindex.MainActivity
import com.sonkins.bikeindex.core.di.viewmodel.ViewModelModule
import com.sonkins.bikeindex.features.about.AboutFragment
import com.sonkins.bikeindex.features.bike.BikeFragment
import com.sonkins.bikeindex.features.bikes.BikesFragment
import com.sonkins.bikeindex.features.bikes.filter.FilterFragment
import com.sonkins.bikeindex.features.bikes.filter.colors.ColorsFragment
import com.sonkins.bikeindex.features.bikes.filter.manufacturers.ManufacturersFragment
import com.sonkins.bikeindex.features.favorites.FavoritesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(mainActivity: MainActivity)

    fun inject(bikesFragment: BikesFragment)
    fun inject(filterFragment: FilterFragment)
    fun inject(colorsFragment: ColorsFragment)
    fun inject(manufacturersFragment: ManufacturersFragment)
    fun inject(aboutFragment: AboutFragment)
    fun inject(bikeFragment: BikeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
}