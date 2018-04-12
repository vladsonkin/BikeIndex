package com.sonkins.bikeindex.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
data class Manufacturers(@SerializedName("manufacturers") val manufacturers: List<Manufacture>?)