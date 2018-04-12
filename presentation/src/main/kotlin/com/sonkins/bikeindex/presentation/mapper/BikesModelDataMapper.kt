package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.presentation.model.BikesModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 05 April 2018.
 */
class BikesModelDataMapper @Inject constructor(private val bikeModelDataMapper: BikeModelDataMapper) {

    fun transform(bikes: Bikes) = BikesModel(
            bikes.bikes?.let {
                bikeModelDataMapper.transform(it)
            }
    )

}