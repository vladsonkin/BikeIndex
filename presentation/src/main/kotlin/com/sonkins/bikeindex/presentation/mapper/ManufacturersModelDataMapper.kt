package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Manufacturers
import com.sonkins.bikeindex.presentation.model.ManufacturersModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
class ManufacturersModelDataMapper @Inject constructor(private val manufactureModelDataMapper: ManufactureModelDataMapper) {

    fun transform(manufacturers: Manufacturers) = ManufacturersModel(
            manufacturers.manufacturers?.let {
                manufactureModelDataMapper.transform(it)
            }
    )

}