package com.sonkins.bikeindex.presentation.ui.main.bikeindex

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.R
import kotlinx.android.synthetic.main.item_bike.view.*

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
class StolenBikesAdapter(private val loadMoreListener: LoadMoreListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_PROGRESSBAR = 0

    private var stolenBikes : ArrayList<Bike?> = ArrayList()

    private var isMoreLoading = true

    fun updateData(stolenBikes: List<Bike>) {
        this.stolenBikes.clear()
        this.stolenBikes.addAll(stolenBikes)
        notifyDataSetChanged()
    }

    fun addNextPage(stolenBikes: List<Bike>) {
        val sizeInit = stolenBikes.size
        this.stolenBikes.addAll(stolenBikes)
        notifyItemRangeChanged(sizeInit, stolenBikes.size)
    }

    fun showLoading(page: Int) {

        if (isMoreLoading) {

            isMoreLoading = false
            Handler().post {
                stolenBikes.add(null)
                notifyItemInserted(stolenBikes.size - 1)
                loadMoreListener.loadMore(page)
            }

        }

    }

    fun setMore(isMore: Boolean) {
        isMoreLoading = isMore
    }

    fun dismissLoading() {
        if (stolenBikes.isNotEmpty()) {
            stolenBikes.removeAt(stolenBikes.size - 1)
            notifyItemRemoved(stolenBikes.size)
        }
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

    interface LoadMoreListener {
        fun loadMore(page: Int)
    }
}