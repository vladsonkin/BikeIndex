package com.sonkins.bikeindex.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Vlad Sonkin
 * on 05 April 2018.
 */
data class Bikes(@SerializedName("bikes") val bikes: List<Bike>?)