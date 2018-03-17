package com.vladsonkin.stolenbikesnl.presentation.ui.stolenbikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladsonkin.stolenbikesnl.presentation.R
import dagger.android.support.DaggerFragment

/**
 * Created by Vlad Sonkin
 * on 17 March 2018.
 */
class StolenBikesFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stolen_bikes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}