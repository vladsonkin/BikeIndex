package com.sonkins.bikeindex.data.repository.datasource

import com.sonkins.bikeindex.data.api.BikeIndexApiService
import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Manufacturers
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
open class BikeApiDataStore @Inject constructor(private val bikeIndexApiService: BikeIndexApiService) {

    fun getBikes(page: Int, perPage: Int) = bikeIndexApiService.getBikes(page, perPage)

    fun getManufacturers(page: Int, perPage: Int) = bikeIndexApiService.getManufacturers(page, perPage)

    fun getColors() = bikeIndexApiService.getColors()
}