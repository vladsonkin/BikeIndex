package com.sonkins.bikeindex.presentation.ui.main.bikes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.BikeModel
import com.sonkins.bikeindex.presentation.ui.base.BasePaginationAdapter
import kotlinx.android.synthetic.main.item_bike.view.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
class BikesAdapter @Inject constructor () : BasePaginationAdapter<BikeModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bike, parent, false)
            BikeViewHolder(view)
        } else {
            super.onCreateViewHolder(parent, viewType)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is BikeViewHolder) {
            holder.bindItem(data[position])
        }

    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(bike: BikeModel?) {
            itemView.textViewBikeName.text = bike?.title
            itemView.textViewBikeSerial.text = bike?.serial
            Glide.with(itemView)
                    .load(bike?.thumb)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder_bike)
                            .error(R.drawable.placeholder_bike))
                    .into(itemView.imageViewBike)
        }

    }
}