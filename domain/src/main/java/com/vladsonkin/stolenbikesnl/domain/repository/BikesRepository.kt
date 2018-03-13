package com.vladsonkin.stolenbikesnl.domain.repository

import com.vladsonkin.stolenbikesnl.domain.model.Bike
import io.reactivex.Observable

/**
 * Created by vsonkin on 13/03/2018.
 */
interface BikesRepository {
    fun searchStolenBikes(page: Int, perPage: Int, location: String, distance: String)
            : Observable<List<Bike>>
}