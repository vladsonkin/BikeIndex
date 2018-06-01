package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Color
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.domain.model.Manufacture
import com.sonkins.bikeindex.presentation.model.ColorModel
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.model.ManufactureModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 27 April 2018.
 */
class FilterMapper @Inject constructor() {

    fun transform(filterModel: FilterModel) = Filter(
            filterModel.color?.let {
                transformColor(it)
            },
            filterModel.manufacture?.let {
                transformManufacture(it)
            },
            filterModel.type,
            filterModel.page,
            filterModel.perPage
    )

    private fun transformColor(colorModel: ColorModel) = Color(
            colorModel.name,
            colorModel.slug
    )

    private fun transformManufacture(manufactureModel: ManufactureModel) = Manufacture(
            manufactureModel.name,
            manufactureModel.companyUrl,
            manufactureModel.id,
            manufactureModel.frameMaker,
            manufactureModel.image,
            manufactureModel.description,
            manufactureModel.slug
    )

}