package com.sonkins.bikeindex.domain.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Vlad Sonkin
 * on 12 April 2018.
 */
data class Manufacture(
		@SerializedName("name") val name: String,
		@SerializedName("company_url") val companyUrl: String?,
		@SerializedName("id") val id: Int,
		@SerializedName("frame_maker") val frameMaker: Boolean?,
		@SerializedName("image") val image: String?,
		@SerializedName("description") val description: String?,
		@SerializedName("slug") val slug: String?
) : Serializable