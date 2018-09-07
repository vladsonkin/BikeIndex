/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.core.platform

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.inflate

abstract class PaginationAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_PROGRESSBAR = 0
    }

    private var isMoreLoading = true
    internal var loadMore: (() -> Unit) = {}
    val mDiffer = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProgressViewHolder(parent.inflate(R.layout.item_progress))
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDiffer.currentList[position] != null) VIEW_TYPE_ITEM else VIEW_TYPE_PROGRESSBAR
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun updateData(data: List<T>) {
        dismissLoading()
        mDiffer.submitList(data)
        isMoreLoading = true
    }

    fun showLoading() {
        if (isMoreLoading) {
            isMoreLoading = false

            Handler().post {
                val listWithProgress = mDiffer.currentList.toMutableList()
                listWithProgress.add(null)
                mDiffer.submitList(listWithProgress)

                loadMore()
            }
        }
    }

    private fun dismissLoading() {
        val currentList = mDiffer.currentList.toMutableList()

        if (currentList.isNotEmpty() && isLoadingItem(currentList.lastIndex)) {
            currentList.removeAt(currentList.lastIndex)
            mDiffer.submitList(currentList)
        }
    }

    private fun isLoadingItem(position: Int) = mDiffer.currentList[position] == null

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}