package com.sonkins.bikeindex.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.sonkins.bikeindex.presentation.di.component.DaggerApplicationComponent
import timber.log.Timber


/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class BikeIndexApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}