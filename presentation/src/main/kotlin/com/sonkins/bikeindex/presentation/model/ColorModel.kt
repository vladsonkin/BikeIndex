package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
@Parcelize
class ColorModel(
        val name: String,
        val slug: String
): Parcelable