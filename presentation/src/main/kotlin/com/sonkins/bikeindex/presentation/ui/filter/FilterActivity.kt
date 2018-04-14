package com.sonkins.bikeindex.presentation.ui.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.router.Router
import com.sonkins.bikeindex.presentation.ui.base.BaseActivity
import io.reactivex.annotations.NonNull
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 11 April 2018.
 */
class FilterActivity : BaseActivity(), FilterFragment.ApplyFilterListener {

    companion object {

        fun startForResult(@NonNull fragment: Fragment, requestCode: Int, filter: Filter) {
            val intent = Intent(fragment.context, FilterActivity::class.java)
            intent.putExtra("FILTER", filter)

            fragment.startActivityForResult(intent, requestCode)
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

    override fun applyFilter() {
        setResult(Activity.RESULT_OK)
        finish()
    }

}