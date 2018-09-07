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

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.sonkins.bikeindex.R

/**
 * Current fragment context for routine work in the scope of particular screen
 */
inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

/**
 * Activity context for sharing data between fragments
 */
inline fun <reified T : ViewModel> Fragment.activityViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this.requireActivity(), factory)[T::class.java]
    vm.body()
    return vm
}

inline fun Fragment.snack(
    @StringRes messageRes: Int,
    @ColorRes color: Int = R.color.colorPrimary,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: Snackbar.() -> Unit
) = snack(resources.getString(messageRes), color, length, action)

inline fun Fragment.snack(
    message: String,
    @ColorRes color: Int = R.color.colorPrimary,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: Snackbar.() -> Unit
): Snackbar? {

    return view?.let {
        val snack = Snackbar.make(it, message, length)
        snack.view.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        snack.action()
        snack.show()

        snack
    }
}

fun Snackbar.action(@StringRes actionRes: Int, @ColorRes color: Int = R.color.white, listener: (View) -> Unit) {
    action(this.view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, @ColorRes color: Int = R.color.white, listener: (View) -> Unit) {
    setAction(action, listener)
    setActionTextColor(ContextCompat.getColor(context, color))
}

fun Fragment.navigateUp() {
    navController().navigateUp()
}

/**
 * Navigate with handling possible runtime exceptions
 */
fun Fragment.navigate(@IdRes actionId: Int) {
    try {
        navController().navigate(actionId)
    } catch (exception: RuntimeException) {
        Crashlytics.logException(exception)
    }
}

/**
 * Navigate with safe-args
 */
fun Fragment.navigate(directions: NavDirections) {
    navigate(directions.actionId, directions.arguments)
}

/**
 * Navigate with handling possible runtime exceptions
 */
fun Fragment.navigate(@IdRes actionId: Int, arguments: Bundle?) {
    try {
        navController().navigate(actionId, arguments)
    } catch (exception: RuntimeException) {
        Crashlytics.logException(exception)
    }
}

/**
 * For internal navigation use only
 */
private fun Fragment.navController() = view?.let { findNavController(it) }
    ?: throw IllegalStateException("Empty view, can't find Navigation Controller")