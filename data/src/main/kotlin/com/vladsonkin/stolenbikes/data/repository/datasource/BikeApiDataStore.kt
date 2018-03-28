package com.vladsonkin.stolenbikes.data.repository.datasource

import com.vladsonkin.stolenbikes.data.api.BikeApiService
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
open class BikeApiDataStore @Inject constructor(private val bikeApiService: BikeApiService) {

    fun getStolenBikes(page: Int,
                       perPage: Int,
                       location: String,
                       distance: String,
                       stolenness: String) : Observable<Response<ResponseBody>> {
        return bikeApiService.getStolenBikes(page, perPage, location, distance, stolenness)
    }

}