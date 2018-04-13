package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Colors
import com.sonkins.bikeindex.presentation.model.ColorsModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
class ColorsModelDataMapper @Inject constructor(private val colorModelDataMapper: ColorModelDataMapper) {

    fun transform(colors: Colors) = ColorsModel(
            colors.colors?.let {
                colorModelDataMapper.transform(it)
            }
    )

}