package com.sonkins.bikeindex.presentation.ui.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 11 April 2018.
 */
class FilterActivity : BaseActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, FilterActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        if (savedInstanceState == null) {
            router.showFilterFragment()
        }
    }

}