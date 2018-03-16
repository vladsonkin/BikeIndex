package com.vladsonkin.stolenbikesnl.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.vladsonkin.stolenbikesnl.presentation.injection.component.DaggerApplicationComponent
import com.vladsonkin.stolenbikesnl.presentation.util.logging.LinkingDebugTree
import timber.log.Timber


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class StolenBikesNLApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(LinkingDebugTree())
    }
}