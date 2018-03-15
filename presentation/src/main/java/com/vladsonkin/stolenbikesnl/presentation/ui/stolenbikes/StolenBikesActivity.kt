package com.vladsonkin.stolenbikesnl.presentation.ui.stolenbikes

import android.os.Bundle
import com.vladsonkin.stolenbikesnl.presentation.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_stolen_bikes.*

/**
 * Created by Vlad Sonkin
 * on 15 March 2018.
 */
class StolenBikesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stolen_bikes)

        setSupportActionBar(toolbar)
    }
}
