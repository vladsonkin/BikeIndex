package com.vladsonkin.stolenbikesnl.presentation.ui.stolenbikes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vladsonkin.stolenbikesnl.R
import kotlinx.android.synthetic.main.activity_stolen_bikes.*

class StolenBikesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stolen_bikes)

        setSupportActionBar(toolbar)
    }
}
