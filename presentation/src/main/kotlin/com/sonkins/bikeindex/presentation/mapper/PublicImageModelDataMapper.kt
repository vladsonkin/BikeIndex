package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.PublicImage
import com.sonkins.bikeindex.presentation.model.PublicImageModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
class PublicImageModelDataMapper @Inject constructor() {

    fun transform(publicImage: PublicImage) = PublicImageModel(
            publicImage.name,
            publicImage.full,
            publicImage.large,
            publicImage.medium,
            publicImage.thumb
    )

    fun transform(publicImages: List<PublicImage>): List<PublicImageModel> {
        val publicImageModels = ArrayList<PublicImageModel>()

        for (publicImage in publicImages) {
            publicImageModels.add(transform(publicImage))
        }

        return publicImageModels
    }

}