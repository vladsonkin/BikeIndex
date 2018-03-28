package com.vladsonkin.stolenbikes.data.mapper

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.vladsonkin.stolenbikes.data.exception.ServerException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Vlad Sonkin
 * on 28 March 2018.
 */
abstract class BaseJsonMapper<T> {

    inline fun <reified T> Gson.fromJson(json: String) =
            try {
                this.fromJson<T>(json, object: TypeToken<T>() {}.type)
            } catch (exception: JsonSyntaxException) {
                throw ServerException()
            }

    abstract fun transform(response: Response<ResponseBody>): T

    protected fun getJsonFromResponse(response: Response<ResponseBody>): JSONObject =
            if (response.isSuccessful) {
                JSONObject(response.body()?.string())
            } else {
                throw ServerException()
            }

}