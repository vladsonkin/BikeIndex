package com.vladsonkin.stolenbikesnl.presentation.injection.module

import com.vladsonkin.stolenbikesnl.presentation.injection.PerActivity
import com.vladsonkin.stolenbikesnl.presentation.ui.stolenbikes.StolenBikesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/
@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindStolenBikesActivity(): StolenBikesActivity

}