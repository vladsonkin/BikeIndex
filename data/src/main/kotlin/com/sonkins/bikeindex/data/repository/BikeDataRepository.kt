package com.sonkins.bikeindex.data.repository

import com.sonkins.bikeindex.data.repository.datasource.BikeApiDataStore
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Colors
import com.sonkins.bikeindex.domain.model.Manufacturers
import com.sonkins.bikeindex.domain.repository.BikeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor(private val bikeApiDataStore: BikeApiDataStore) : BikeRepository {

    override fun getBikes(page: Int, perPage: Int) = bikeApiDataStore.getBikes(page, perPage)

    override fun getManufacturers(page: Int, perPage: Int) = bikeApiDataStore.getManufacturers(page, perPage)

    override fun getColors() = bikeApiDataStore.getColors()
}