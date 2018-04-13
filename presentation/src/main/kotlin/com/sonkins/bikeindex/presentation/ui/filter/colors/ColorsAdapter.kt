package com.sonkins.bikeindex.presentation.ui.filter.colors

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.ColorModel
import kotlinx.android.synthetic.main.item_color.view.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 13 April 2018.
 */
class ColorsAdapter @Inject constructor() : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    private val data: MutableList<ColorModel> = mutableListOf()

    fun setData(colors: List<ColorModel>?) {
        colors?.let {
            data.clear()
            data.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindItem(data[position])
    }


    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(colorModel: ColorModel?) {
            itemView.textViewColorName.text = colorModel?.name
        }

    }
}