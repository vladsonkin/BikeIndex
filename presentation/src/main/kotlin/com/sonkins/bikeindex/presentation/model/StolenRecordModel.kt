package com.sonkins.bikeindex.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
@Parcelize
class StolenRecordModel (
        val dateStolen: Int,
        val location: String,
        val latitude: Double?,
        val longitude: Double?,
        val theftDescription: String?,
        val lockingDescription: String?,
        val lockDefeatDescription: String?,
        val policeReportNumber: String?,
        val policeReportDepartment: String?,
        val createdAt: Int?,
        val createOpen311: Boolean?,
        val id: Int?
) : Parcelable