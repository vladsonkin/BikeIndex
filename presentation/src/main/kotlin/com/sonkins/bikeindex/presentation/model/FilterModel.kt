package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 27 April 2018.
 */
@Parcelize
class FilterModel(
        var color: ColorModel? = null,
        var manufacture: ManufactureModel? = null,
        var type: String = "All",
        var page: Int = 1,
        var perPage: Int = 10
) : Parcelable