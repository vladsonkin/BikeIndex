package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
@Parcelize
class PublicImageModel (
        val name: String?,
        val full: String?,
        val large: String?,
        val medium: String?,
        val thumb: String?
) : Parcelable