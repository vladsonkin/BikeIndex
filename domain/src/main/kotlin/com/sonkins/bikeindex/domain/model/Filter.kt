package com.sonkins.bikeindex.domain.model

import java.io.Serializable

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
data class Filter(
        var color: Color? = null,
        var manufacture: Manufacture? = null,
        var type: String,
        var page: Int,
        var perPage: Int
) : Serializable