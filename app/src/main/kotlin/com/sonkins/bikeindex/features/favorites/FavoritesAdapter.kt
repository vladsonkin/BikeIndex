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

package com.sonkins.bikeindex.features.favorites

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.inflate
import com.sonkins.bikeindex.core.extension.invisible
import com.sonkins.bikeindex.core.extension.loadFromUrl
import com.sonkins.bikeindex.core.extension.visible
import kotlinx.android.synthetic.main.item_bike.view.*
import javax.inject.Inject

class FavoritesAdapter @Inject constructor() :
    ListAdapter<FavoriteBikesModel.BikeModel, FavoritesAdapter.BikeViewHolder>(diffCallback) {

    internal var onFavoriteBikeClick: ((FavoriteBikesModel.BikeModel) -> Unit) = { _ -> }
    internal var onFavoriteBikeShareClick: ((Int) -> Unit) = { _ -> }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<FavoriteBikesModel.BikeModel>() {
            override fun areItemsTheSame(oldItem: FavoriteBikesModel.BikeModel, newItem: FavoriteBikesModel.BikeModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FavoriteBikesModel.BikeModel,
                newItem: FavoriteBikesModel.BikeModel
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeViewHolder {
        return BikeViewHolder(parent.inflate(R.layout.item_bike)).apply {
            itemView.container.setOnClickListener {
                onFavoriteBikeClick.invoke(getItem(adapterPosition))
            }

            itemView.btnShare.setOnClickListener {
                onFavoriteBikeShareClick.invoke(getItem(adapterPosition).id)
            }

            itemView.btnExplore.setOnClickListener {
                onFavoriteBikeClick.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: BikeViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class BikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(bikeModel: FavoriteBikesModel.BikeModel) {
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