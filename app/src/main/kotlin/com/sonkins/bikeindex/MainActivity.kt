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

package com.sonkins.bikeindex

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.sonkins.bikeindex.core.extension.hideKeyboard
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_layout.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Change splash screen theme to normal one
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        NavigationUI.setupWithNavController(bottomNavigation, findNavController(fragmentContainer))
    }

    override fun onSupportNavigateUp(): Boolean {
        container.hideKeyboard()
        return findNavController(fragmentContainer).navigateUp()
    }
}