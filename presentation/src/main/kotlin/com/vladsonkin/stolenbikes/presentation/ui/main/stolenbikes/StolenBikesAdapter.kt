package com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vladsonkin.stolenbikes.domain.model.Bike
import com.vladsonkin.stolenbikes.presentation.R
import kotlinx.android.synthetic.main.item_bike.view.*

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
class StolenBikesAdapter : RecyclerView.Adapter<StolenBikesAdapter.BikeViewHolder>() {

    private var stolenBikes : List<Bike> = ArrayList()

    fun setData(stolenBikes: List<Bike>) {
        this.stolenBikes = stolenBikes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bike, parent, false)
        return BikeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stolenBikes.size
    }

    override fun onBindViewHolder(holder: BikeViewHolder, position: Int) {
        holder.bindItem(stolenBikes.get(position))
    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(bike: Bike) {
            itemView.textViewBikeName.text = bike.title
            Glide.with(itemView).load(bike.thumb).into(itemView.imageViewBike)
        }

    }
}