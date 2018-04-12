package com.sonkins.bikeindex.data.api

import com.sonkins.bikeindex.domain.model.Bikes
import com.sonkins.bikeindex.domain.model.Manufacturers
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
interface BikeIndexApiService {

    @GET("search")
    fun getBikes(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("location") location: String,
            @Query("distance") distance: String,
            @Query("stolenness") stolenness: String
    ) : Observable<Bikes>

    @GET("manufacturers")
    fun getManufacturers(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ) : Observable<Manufacturers>

}