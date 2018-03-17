package com.vladsonkin.stolenbikes.presentation.di.component

import com.vladsonkin.stolenbikes.presentation.StolenBikesApplication
import com.vladsonkin.stolenbikes.presentation.di.PerApplication
import com.vladsonkin.stolenbikes.presentation.di.module.ActivityBuilderModule
import com.vladsonkin.stolenbikes.presentation.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@PerApplication
@Component(modules = arrayOf(
        ActivityBuilderModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent : AndroidInjector<StolenBikesApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<StolenBikesApplication>()

}