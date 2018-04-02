package com.sonkins.bikeindex.data.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
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
    ) : Observable<Response<ResponseBody>>

}