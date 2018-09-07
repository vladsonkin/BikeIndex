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

package com.sonkins.bikeindex.features.bikes.filter.colors

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sonkins.bikeindex.R
import com.sonkins.bikeindex.core.extension.inflate
import kotlinx.android.synthetic.main.item_color.view.*
import javax.inject.Inject

class ColorsAdapter @Inject constructor() : ListAdapter<ColorModel, ColorsAdapter.ColorViewHolder>(diffCallback) {
    internal var onColorClick: ((ColorModel) -> Unit) = { _ -> }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ColorModel>() {
            override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel) =
                oldItem.slug == newItem.slug

            override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(parent.inflate(R.layout.item_color)).apply {
            itemView.setOnClickListener {
                onColorClick.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(colorModel: ColorModel?) {
            itemView.textViewColorName.text = colorModel?.name
        }
    }
}