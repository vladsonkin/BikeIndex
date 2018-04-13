package com.sonkins.bikeindex.domain.repository

import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Colors
import com.sonkins.bikeindex.domain.model.Manufacturers
import io.reactivex.Observable

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
interface BikeRepository {
    fun getBikes(page: Int, perPage: Int): Observable<Bikes>

    fun getManufacturers(page: Int, perPage: Int): Observable<Manufacturers>

    fun getColors(): Observable<Colors>
}