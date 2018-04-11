package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 05 April 2018.
 */
@Parcelize
class BikesModel (val bikes: List<BikeModel>?) : Parcelable