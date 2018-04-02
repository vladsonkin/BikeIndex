package com.sonkins.bikeindex.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
data class PublicImage(
        @SerializedName("name") val name: String,
        @SerializedName("full") val full: String,
        @SerializedName("large") val large: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("thumb") val thumb: String
)