package com.sonkins.bikeindex.presentation.ui.filter

import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vlad Sonkin
 * on 11 April 2018.
 */
@Module
interface FilterActivityModule {

    @ContributesAndroidInjector(modules = [FilterFragmentModule::class])
    fun filterFragmentInjector(): FilterFragment

    @Binds
    fun bindContext(context: FilterActivity): BaseActivity

}