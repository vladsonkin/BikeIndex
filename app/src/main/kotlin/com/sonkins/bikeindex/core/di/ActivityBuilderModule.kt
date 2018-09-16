package com.sonkins.bikeindex.core.di

import com.sonkins.bikeindex.MainActivity
import com.sonkins.bikeindex.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}