package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
@Parcelize
class ComponentModel(
        val id: Int,
        val description: String,
        val serialNumber: Int?,
        val componentType: String?,
        val componentGroup: String?,
        val rear: Boolean?,
        val front: Boolean?,
        val manufacturerName: String?,
        val modelName: String?,
        val year: Int?
) : Parcelable