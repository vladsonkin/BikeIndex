package com.sonkins.bikeindex.presentation.ui.filter.manufacturers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.ManufactureModel
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import kotlinx.android.synthetic.main.item_manufacturer.view.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
class ManufacturersAdapter @Inject constructor() : BasePaginationAdapter<ManufactureModel>()  {

    private lateinit var manufactureClickListener: ManufactureClickListener

    fun setClickListener(manufactureClickListener: ManufactureClickListener) {
        this.manufactureClickListener = manufactureClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manufacturer, parent, false)

            val holder = ManufacturerViewHolder(view)

            holder.itemView.setOnClickListener {
                data[holder.adapterPosition]?.let {
                    manufactureClickListener.manufactureClick(it)
                }
            }

            holder

        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ManufacturerViewHolder) {
            holder.bindItem(data[position])
        }
    }

    class ManufacturerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(manufacture: ManufactureModel?) {
            itemView.textViewManufacturerName.text = manufacture?.name
        }

    }

    interface ManufactureClickListener {
        fun manufactureClick(manufactureModel: ManufactureModel)
    }
}