/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.core.platform

import android.util.Log
import com.sonkins.bikeindex.core.exception.ConnectionException
import com.sonkins.bikeindex.core.exception.ServerException
import okhttp3.Headers
import retrofit2.Call
import javax.inject.Inject

abstract class BaseNetwork {
    @Inject
    lateinit var networkHandler: NetworkHandler

    fun <T> request(call: Call<T>): Pair<T?, Headers> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    val response = call.execute()

                    when (response.isSuccessful) {
                        true -> Pair(response.body(), response.headers())
                        false -> throw ServerException(response.errorBody().toString())
                    }
                } catch (exception: Throwable) {
                    throw ServerException(Log.getStackTraceString(exception))
                }
            }
            false, null -> throw ConnectionException("No Internet Connection")
        }
    }
}