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

package com.sonkins.bikeindex.core.di

import com.sonkins.bikeindex.BikeIndexApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ActivityBuilderModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<BikeIndexApplication> {
    override fun inject(instance: BikeIndexApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BikeIndexApplication): Builder

        fun build(): ApplicationComponent
    }
}