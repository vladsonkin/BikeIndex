package com.sonkins.bikeindex.domain.model

import java.io.Serializable

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
data class Filter(
        var color: Color? = null,
        var manufacture: Manufacture? = null,
        var page: Int = 1,
        var perPage: Int = 10
) : Serializable