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

package com.sonkins.bikeindex.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.Companion.empty() = ""

fun String.Companion.makeStolenInfo(dateStolen: Int?, stolenLocation: String?): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("Stolen")

    if (dateStolen != null && dateStolen > 0) {
        stringBuilder.append(" ${toStringDate(dateStolen)}")
    }

    stolenLocation.takeIf { !it.isNullOrEmpty() }?.let {
        stringBuilder.append(" in $it")
    }

    return stringBuilder.toString()
}

fun String.Companion.toStringDate(timestamp: Int) = toStringDate(timestamp.toLong())

fun String.Companion.toStringDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("d MMMM yyyy", Locale.US)
    val netDate = Date(timestamp * 1000)
    return sdf.format(netDate)
}

