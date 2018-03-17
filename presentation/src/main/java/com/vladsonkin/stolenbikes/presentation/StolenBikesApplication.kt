package com.vladsonkin.stolenbikes.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.vladsonkin.stolenbikes.presentation.di.component.DaggerApplicationComponent
import com.vladsonkin.stolenbikes.presentation.util.logging.LinkingDebugTree
import timber.log.Timber


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class StolenBikesApplication : DaggerApplication() {

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