package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Manufacture
import com.sonkins.bikeindex.presentation.model.ManufactureModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
class ManufactureModelDataMapper @Inject constructor() {

    fun transform(manufacture: Manufacture) = ManufactureModel(
            manufacture.name,
            manufacture.companyUrl,
            manufacture.id,
            manufacture.frameMaker,
            manufacture.image,
            manufacture.description,
            manufacture.slug
    )

    fun transform(manufactures: List<Manufacture>): List<ManufactureModel> {
        val manufactureModels = ArrayList<ManufactureModel>()

        for (manufacture in manufactures) {
            manufactureModels.add(transform(manufacture))
        }

        return manufactureModels
    }

}