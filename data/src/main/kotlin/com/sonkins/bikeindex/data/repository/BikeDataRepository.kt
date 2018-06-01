package com.sonkins.bikeindex.data.repository

import com.sonkins.bikeindex.data.repository.datasource.BikeApiDataStore
import com.sonkins.bikeindex.domain.repository.BikeRepository
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeDataRepository @Inject constructor(private val bikeApiDataStore: BikeApiDataStore)
    : BikeRepository {

    override fun getBikes(manufacturer: String?, color: String?, type: String, page: Int, perPage: Int)
            = bikeApiDataStore.getBikes(manufacturer, color, type, page, perPage)

    override fun getManufacturers(page: Int, perPage: Int)
            = bikeApiDataStore.getManufacturers(page, perPage)

    override fun getColors() = bikeApiDataStore.getColors()
}