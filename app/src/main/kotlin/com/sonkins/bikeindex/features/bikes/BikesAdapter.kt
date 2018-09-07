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

package com.sonkins.bikeindex.features.bikes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.inflate
import com.sonkins.bikeindex.core.extension.invisible
import com.sonkins.bikeindex.core.extension.loadFromUrl
import com.sonkins.bikeindex.core.extension.visible
import com.sonkins.bikeindex.core.platform.PaginationAdapter
import kotlinx.android.synthetic.main.item_bike.view.*
import javax.inject.Inject

class BikesAdapter @Inject constructor() : PaginationAdapter<BikesModel.BikeModel>(diffCallback) {

    internal var onBikeClick: ((Int) -> Unit) = { _ -> }
    internal var onBikeShareClick: ((Int) -> Unit) = { _ -> }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<BikesModel.BikeModel>() {
            override fun areItemsTheSame(oldItem: BikesModel.BikeModel, newItem: BikesModel.BikeModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BikesModel.BikeModel, newItem: BikesModel.BikeModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            BikeViewHolder(parent.inflate(R.layout.item_bike)).apply {
                itemView.container.setOnClickListener {
                    onBikeClick.invoke(mDiffer.currentList[adapterPosition].id)
                }

                itemView.btnShare.setOnClickListener {
                    onBikeShareClick.invoke(mDiffer.currentList[adapterPosition].id)
                }

                itemView.btnExplore.setOnClickListener {
                    onBikeClick.invoke(mDiffer.currentList[adapterPosition].id)
                }
            }
        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BikeViewHolder) {
            holder.bindItem(mDiffer.currentList[position])
        }
    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(bikeModel: BikesModel.BikeModel) {
            itemView.imageViewBike.loadFromUrl(bikeModel.largeImg, R.drawable.ic_bike_200)

            bikeModel.stolenInfo?.let {
                itemView.textViewStolenInfo.visible()
                itemView.textViewStolenInfo.text = it
            } ?: itemView.textViewStolenInfo.invisible()

            itemView.textViewBikeName.text = bikeModel.title
            itemView.textViewBikeSerial.text = bikeModel.serial
            itemView.textViewBikeManufacturer.text = bikeModel.manufacturerName
            itemView.textViewBikeColor.text = bikeModel.frameColors
        }
    }
}