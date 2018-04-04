package com.sonkins.bikeindex.domain.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
data class Component(
		@SerializedName("id") val id: Int,
		@SerializedName("description") val description: String,
		@SerializedName("serial_number") val serialNumber: Int?,
		@SerializedName("component_type") val componentType: String?,
		@SerializedName("component_group") val componentGroup: String?,
		@SerializedName("rear") val rear: Boolean?,
		@SerializedName("front") val front: Boolean?,
		@SerializedName("manufacturer_name") val manufacturerName: String?,
		@SerializedName("model_name") val modelName: String?,
		@SerializedName("year") val year: Int?
)
