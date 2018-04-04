package com.sonkins.bikeindex.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
data class StolenRecord(
        @SerializedName("date_stolen") val dateStolen: Int,
        @SerializedName("location") val location: String,
        @SerializedName("latitude") val latitude: Double?,
        @SerializedName("longitude") val longitude: Double?,
        @SerializedName("theft_description") val theftDescription: String?,
        @SerializedName("locking_description") val lockingDescription: String?,
        @SerializedName("lock_defeat_description") val lockDefeatDescription: String?,
        @SerializedName("police_report_number") val policeReportNumber: String?,
        @SerializedName("police_report_department") val policeReportDepartment: String?,
        @SerializedName("created_at") val createdAt: Int?,
        @SerializedName("create_open311") val createOpen311: Boolean?,
        @SerializedName("id") val id: Int?
)