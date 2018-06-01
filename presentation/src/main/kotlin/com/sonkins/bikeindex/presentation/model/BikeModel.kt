package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
@Parcelize
class BikeModel (
        val id: Int,
        val title: String?,
        val serial: String?,
        val manufacturerName: String?,
        val frameModel: String?,
        val year: Int?,
        val frameColors: List<String>?,
        val thumb: String?,
        val largeImg: String?,
        val isStockImg: Boolean?,
        val stolen: Boolean?,
        val stolenLocation: String?,
        val dateStolen: Int?,
        val registrationCreatedAt: Int?,
        val registrationUpdatedAt: Int?,
        val url: String?,
        val apiUrl: String?,
        val manufacturerId: Int?,
        val paintDescription: String?,
        val name: String?,
        val frameSize: String?,
        val description: String?,
        val rearTireNarrow: Boolean?,
        val frontTireNarrow: Boolean?,
        val typeOfCycle: String?,
        val testBike: Boolean?,
        val rearWheelSizeIsoBsd: Int?,
        val frontWheelSizeIsoBsd: Int?,
        val handlebarTypeSlug: String?,
        val frameMaterialSlug: String?,
        val frontGearTypeSlug: String?,
        val rearGearTypeSlug: String?,
        val stolenRecord: StolenRecordModel?,
        val publicImages: List<PublicImageModel>?,
        val components: List<ComponentModel>?
) : Parcelable