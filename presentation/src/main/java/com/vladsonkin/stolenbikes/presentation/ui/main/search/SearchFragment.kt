package com.vladsonkin.stolenbikes.presentation.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.base.BaseFragment

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 */
class SearchFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}