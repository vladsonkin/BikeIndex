package com.sonkins.bikeindex.domain.repository

import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Manufacturers
import io.reactivex.Observable

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
interface BikeRepository {
    fun getBikes(page: Int, perPage: Int, location: String, distance: String, stolenness: String)
            : Observable<Bikes>

    fun getManufacturers(page: Int, perPage: Int) : Observable<Manufacturers>
}