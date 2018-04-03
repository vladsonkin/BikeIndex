package com.sonkins.bikeindex.presentation.di.component

import com.sonkins.bikeindex.presentation.BikeIndexApplication
import com.sonkins.bikeindex.presentation.di.ApplicationScope
import com.sonkins.bikeindex.presentation.di.module.ActivityBuilderModule
import com.sonkins.bikeindex.presentation.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@ApplicationScope
@Component(modules = arrayOf(
        ActivityBuilderModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent : AndroidInjector<BikeIndexApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BikeIndexApplication>()

}