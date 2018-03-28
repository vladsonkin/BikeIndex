package com.vladsonkin.stolenbikes.presentation.ui.main.info.di

import com.vladsonkin.stolenbikes.presentation.ui.main.info.InfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
@Module
abstract class InfoFragmentBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(InfoFragmentModule::class))
    abstract fun provideInfoFragmentFactory(): InfoFragment

}