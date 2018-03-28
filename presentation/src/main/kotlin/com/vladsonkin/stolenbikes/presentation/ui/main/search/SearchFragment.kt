package com.vladsonkin.stolenbikes.presentation.ui.main.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
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
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_search)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

}