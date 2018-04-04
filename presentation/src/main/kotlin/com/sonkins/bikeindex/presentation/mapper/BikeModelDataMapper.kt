package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.model.BikeModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 *
 * Mapper class used to transform {@link Bike} (in the domain layer) to {@link BikeModel} in the
 * presentation layer.
 */
class BikeModelDataMapper @Inject constructor(
        val componentModelDataMapper: ComponentModelDataMapper,
        val publicImageModelDataMapper: PublicImageModelDataMapper,
        val stolenRecordModelDataMapper: StolenRecordModelDataMapper) {

    fun transform(bike: Bike) = BikeModel(
            bike.id,
            bike.title,
            bike.serial,
            bike.manufacturerName,
            bike.frameModel,
            bike.year,
            bike.frameColors,
            bike.thumb,
            bike.largeImg,
            bike.isStockImg,
            bike.stolen,
            bike.stolenLocation,
            bike.dateStolen,
            bike.registrationCreatedAt,
            bike.registrationUpdatedAt,
            bike.url,
            bike.apiUrl,
            bike.manufacturerId,
            bike.paintDescription,
            bike.name,
            bike.frameSize,
            bike.description,
            bike.rearTireNarrow,
            bike.frontTireNarrow,
            bike.typeOfCycle,
            bike.testBike,
            bike.rearWheelSizeIsoBsd,
            bike.frontWheelSizeIsoBsd,
            bike.handlebarTypeSlug,
            bike.frameMaterialSlug,
            bike.frontGearTypeSlug,
            bike.rearGearTypeSlug,

            bike.stolenRecord?.let {
                stolenRecordModelDataMapper.transform(it)
            },

            bike.publicImages?.let {
                publicImageModelDataMapper.transform(it)
            },

            bike.components?.let {
                componentModelDataMapper.transform(it)
            }
    )

    fun transform(bikes: List<Bike>): List<BikeModel> {
        val bikeModels = ArrayList<BikeModel>()

        for (bike in bikes) {
            bikeModels.add(transform(bike))
        }

        return bikeModels
    }

}