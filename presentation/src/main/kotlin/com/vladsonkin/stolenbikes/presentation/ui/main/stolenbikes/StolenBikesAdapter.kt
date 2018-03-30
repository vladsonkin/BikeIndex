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
class StolenBikesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_PROGRESSBAR = -1

    private var stolenBikes : ArrayList<Bike?> = ArrayList()

    fun updateData(stolenBikes: List<Bike>) {
        this.stolenBikes.clear()
        this.stolenBikes.addAll(stolenBikes)
        notifyDataSetChanged()
    }

    fun addNextPage(stolenBikes: List<Bike>) {
        this.stolenBikes.addAll(stolenBikes)
        notifyItemRangeInserted(itemCount, stolenBikes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bike, parent, false)
            BikeViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            ProgressViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return stolenBikes.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (stolenBikes.get(position) != null) VIEW_TYPE_ITEM else VIEW_TYPE_PROGRESSBAR
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is BikeViewHolder) {
            holder.bindItem(stolenBikes.get(position))
        }

    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(bike: Bike?) {
            itemView.textViewBikeName.text = bike?.title
            Glide.with(itemView).load(bike?.thumb).into(itemView.imageViewBike)
        }

    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}