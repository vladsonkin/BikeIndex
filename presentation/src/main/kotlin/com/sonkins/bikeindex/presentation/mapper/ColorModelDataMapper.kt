package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Color
import com.sonkins.bikeindex.presentation.model.ColorModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
class ColorModelDataMapper @Inject constructor() {

    fun transform(color: Color) = ColorModel(
            color.name,
            color.slug
    )

    fun transform(colors: List<Color>): List<ColorModel> {
        val colorModels = ArrayList<ColorModel>()

        for (color in colors) {
            colorModels.add(transform(color))
        }

        return colorModels
    }

}