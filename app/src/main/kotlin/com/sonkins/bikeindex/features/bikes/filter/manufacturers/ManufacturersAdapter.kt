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

package com.sonkins.bikeindex.features.bikes.filter.manufacturers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.platform.PaginationAdapter
import kotlinx.android.synthetic.main.item_manufacturer.view.*
import javax.inject.Inject

class ManufacturersAdapter @Inject constructor() : PaginationAdapter<ManufacturerModel>(diffCallback) {

    internal var onManufacturerClick: ((ManufacturerModel) -> Unit) = { _ -> }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ManufacturerModel>() {
            override fun areItemsTheSame(oldItem: ManufacturerModel, newItem: ManufacturerModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ManufacturerModel, newItem: ManufacturerModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manufacturer, parent, false)

            ManufacturerViewHolder(view).apply {
                itemView.setOnClickListener {
                    onManufacturerClick.invoke(mDiffer.currentList[adapterPosition])
                }
            }
        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ManufacturerViewHolder) {
            holder.bind(mDiffer.currentList[position])
        }
    }

    class ManufacturerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(manufacturer: ManufacturerModel) {
            itemView.textViewManufacturerName.text = manufacturer.name
        }
    }
}