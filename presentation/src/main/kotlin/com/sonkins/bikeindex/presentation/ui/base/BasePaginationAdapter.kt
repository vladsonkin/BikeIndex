package com.sonkins.bikeindex.presentation.ui.base

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonkins.bikeindex.presentation.R

/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
abstract class BasePaginationAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_PROGRESSBAR = 0

    internal var data: ArrayList<T?> = ArrayList()
    private var isMoreLoading = true
    private lateinit var loadMoreListener: LoadMoreListener

    fun setLoadMoreListener(loadMoreListener: LoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun updateData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addNextPage(data: List<T>, isMore: Boolean) {

        dismissLoading()

        val sizeInit = data.size
        this.data.addAll(data)
        notifyItemRangeChanged(sizeInit, data.size)

        setMore(isMore)
    }

    fun showLoading(page: Int) {
        if (isMoreLoading) {
            isMoreLoading = false
            Handler().post {
                data.add(null)
                notifyItemInserted(data.size - 1)
                loadMoreListener.loadMore(page)
            }
        }
    }

    private fun dismissLoading() {
        if (data.isNotEmpty()) {
            data.removeAt(data.size - 1)
            notifyItemRemoved(data.size)
        }
    }

    private fun setMore(isMore: Boolean) {
        isMoreLoading = isMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        return ProgressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.get(position) != null) VIEW_TYPE_ITEM else VIEW_TYPE_PROGRESSBAR
    }

    fun isLoadingItem(position: Int): Boolean {
        return this.data[position] == null
    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface LoadMoreListener {
        fun loadMore(page: Int)
    }
}