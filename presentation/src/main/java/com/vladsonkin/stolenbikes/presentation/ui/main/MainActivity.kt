package com.vladsonkin.stolenbikes.presentation.ui.main

import android.os.Bundle
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesFragment
import kotlinx.android.synthetic.main.activity_stolen_bikes.*

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
open class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stolen_bikes)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, StolenBikesFragment())
        }
    }
}
