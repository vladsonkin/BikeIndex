package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
@Parcelize
class ManufactureModel (
        val name: String,
        val companyUrl: String?,
        val id: Int,
        val frameMaker: Boolean?,
        val image: String?,
        val description: String?,
        val slug: String?
) : Parcelable