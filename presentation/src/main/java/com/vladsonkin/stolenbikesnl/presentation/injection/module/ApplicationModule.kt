package com.vladsonkin.stolenbikesnl.presentation.injection.module

import dagger.Module

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module(includes = arrayOf(ApiServiceModule::class))
open class ApplicationModule {

}