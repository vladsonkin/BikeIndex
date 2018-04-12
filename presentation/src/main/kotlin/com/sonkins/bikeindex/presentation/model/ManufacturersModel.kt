package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
@Parcelize
class ManufacturersModel(val manufacturers: List<ManufactureModel>?) : Parcelable