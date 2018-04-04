package com.sonkins.bikeindex.presentation.ui.bikes

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sonkins.bikeindex.domain.model.Bike
import com.sonkins.bikeindex.presentation.R
import com.sonkins.bikeindex.presentation.model.BikeModel
import kotlinx.android.synthetic.main.item_bike.view.*
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
class BikesAdapter @Inject constructor () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_PROGRESSBAR = 0

    private var bikes : ArrayList<BikeModel?> = ArrayList()

    private var isMoreLoading = true

    private lateinit var loadMoreListener: LoadMoreListener

    fun setListener(loadMoreListener: LoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun updateData(bikes: List<BikeModel>) {
        this.bikes.clear()
        this.bikes.addAll(bikes)
        notifyDataSetChanged()
    }

    fun addNextPage(bikes: List<BikeModel>) {
        val sizeInit = bikes.size
        this.bikes.addAll(bikes)
        notifyItemRangeChanged(sizeInit, bikes.size)
    }

    fun showLoading(page: Int) {

        if (isMoreLoading) {

            isMoreLoading = false
            Handler().post {
                bikes.add(null)
                notifyItemInserted(bikes.size - 1)
                loadMoreListener.loadMore(page)
            }

        }

    }

    fun setMore(isMore: Boolean) {
        isMoreLoading = isMore
    }

    fun dismissLoading() {
        if (bikes.isNotEmpty()) {
            bikes.removeAt(bikes.size - 1)
            notifyItemRemoved(bikes.size)
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
        return bikes.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (bikes.get(position) != null) VIEW_TYPE_ITEM else VIEW_TYPE_PROGRESSBAR
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is BikeViewHolder) {
            holder.bindItem(bikes.get(position))
        }

    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(bike: BikeModel?) {
            itemView.textViewBikeName.text = bike?.title
            Glide.with(itemView).load(bike?.thumb).into(itemView.imageViewBike)
        }

    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface LoadMoreListener {
        fun loadMore(page: Int)
    }
}