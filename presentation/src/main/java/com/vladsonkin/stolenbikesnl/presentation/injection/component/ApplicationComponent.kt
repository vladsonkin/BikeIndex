package com.vladsonkin.stolenbikesnl.presentation.injection.component

import com.vladsonkin.stolenbikesnl.presentation.StolenBikesNLApplication
import com.vladsonkin.stolenbikesnl.presentation.injection.PerApplication
import com.vladsonkin.stolenbikesnl.presentation.injection.module.ActivityBuilderModule
import com.vladsonkin.stolenbikesnl.presentation.injection.module.ApplicationModule
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
interface ApplicationComponent : AndroidInjector<StolenBikesNLApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<StolenBikesNLApplication>()

}