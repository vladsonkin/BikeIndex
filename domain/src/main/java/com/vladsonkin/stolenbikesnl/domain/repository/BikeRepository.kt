package com.vladsonkin.stolenbikesnl.domain.repository

import com.vladsonkin.stolenbikesnl.domain.model.Bike
import io.reactivex.Observable

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
interface BikeRepository {
    fun searchStolenBikes(page: Int, perPage: Int, location: String, distance: String)
            : Observable<List<Bike>>
}