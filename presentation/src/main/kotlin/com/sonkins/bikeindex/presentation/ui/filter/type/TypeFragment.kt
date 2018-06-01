package com.sonkins.bikeindex.presentation.ui.filter.type

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.FilterModel
import com.sonkins.bikeindex.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_type.*

/**
 * Created by Vlad Sonkin
 * on 27 April 2018.
 */
class TypeFragment : BaseFragment() {

    private lateinit var filterModel: FilterModel

    companion object {

        const val ARG_FILTER = "arg_filter"

        fun newInstance(filterModel: FilterModel): TypeFragment {
            val args = Bundle()
            args.putParcelable(ARG_FILTER, filterModel)
            val fragment = TypeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_type, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.type)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        filterModel = arguments?.getParcelable(ARG_FILTER)!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewAll.setOnClickListener {
            filterModel.type = "All"
            sendResult()
        }

        textViewStolen.setOnClickListener {
            filterModel.type = "Stolen"
            sendResult()
        }

        textViewNotStolen.setOnClickListener {
            filterModel.type = "Not stolen"
            sendResult()
        }
    }

    private fun sendResult() {
        val intent = Intent(context, TypeFragment::class.java)
        intent.putExtra(ARG_FILTER, filterModel)
        activity?.onBackPressed()
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showError(message: String) {}


}