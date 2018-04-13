package com.sonkins.bikeindex.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
data class Color(
        @SerializedName("name") val name: String,
        @SerializedName("slug") val slug: String
)