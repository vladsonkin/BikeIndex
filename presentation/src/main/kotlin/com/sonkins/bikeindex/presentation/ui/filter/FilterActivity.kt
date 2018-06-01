package com.sonkins.bikeindex.presentation.ui.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.sonkins.bikeindex.domain.model.Filter
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.FilterModel
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

        const val ARG_FILTER = "arg_filter"

        fun startForResult(@NonNull fragment: Fragment, requestCode: Int, filterModel: FilterModel) {
            val intent = Intent(fragment.context, FilterActivity::class.java)
            intent.putExtra(ARG_FILTER, filterModel)

            fragment.startActivityForResult(intent, requestCode)
        }
    }

    @Inject lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        if (savedInstanceState == null) {
            router.showFilterFragment(intent.getParcelableExtra(ARG_FILTER))
        }
    }

    override fun applyFilter(filterModel: FilterModel) {
        val intent = Intent()
        intent.putExtra(ARG_FILTER, filterModel)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}