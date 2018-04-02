package com.sonkins.bikeindex.data.mapper

import com.google.gson.Gson
import com.sonkins.bikeindex.domain.model.Bike
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
open class BikesMapper @Inject constructor(private val gson: Gson) : BaseJsonMapper<List<Bike>>() {

    override fun transform(response: Response<ResponseBody>): List<Bike> {
        val resultJson = JSONObject(response.body()?.string())
        val bikesJsonArray = resultJson.opt("bikes")

        return gson.fromJson<List<Bike>>(bikesJsonArray.toString())
    }

}