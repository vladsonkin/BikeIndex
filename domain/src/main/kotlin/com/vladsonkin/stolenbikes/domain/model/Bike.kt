package com.vladsonkin.stolenbikes.domain.model
import com.google.gson.annotations.SerializedName


/**
* Created by Vlad Sonkin
* on 15 March 2018.
*/

data class Bike(
        @SerializedName("id") val id: Int,
		@SerializedName("title") val title: String,
		@SerializedName("serial") val serial: String,
		@SerializedName("manufacturer_name") val manufacturerName: String,
		@SerializedName("frame_model") val frameModel: String,
		@SerializedName("year") val year: Any,
		@SerializedName("frame_colors") val frameColors: List<String>,
		@SerializedName("thumb") val thumb: String,
		@SerializedName("large_img") val largeImg: String,
		@SerializedName("is_stock_img") val isStockImg: Boolean,
		@SerializedName("stolen") val stolen: Boolean,
		@SerializedName("stolen_location") val stolenLocation: String,
		@SerializedName("date_stolen") val dateStolen: Int,
		@SerializedName("registration_created_at") val registrationCreatedAt: Int,
		@SerializedName("registration_updated_at") val registrationUpdatedAt: Int,
		@SerializedName("url") val url: String,
		@SerializedName("api_url") val apiUrl: String,
		@SerializedName("manufacturer_id") val manufacturerId: Int,
		@SerializedName("paint_description") val paintDescription: Any,
		@SerializedName("name") val name: Any,
		@SerializedName("frame_size") val frameSize: Any,
		@SerializedName("description") val description: Any,
		@SerializedName("rear_tire_narrow") val rearTireNarrow: Boolean,
		@SerializedName("front_tire_narrow") val frontTireNarrow: Any,
		@SerializedName("type_of_cycle") val typeOfCycle: String,
		@SerializedName("test_bike") val testBike: Boolean,
		@SerializedName("rear_wheel_size_iso_bsd") val rearWheelSizeIsoBsd: Any,
		@SerializedName("front_wheel_size_iso_bsd") val frontWheelSizeIsoBsd: Any,
		@SerializedName("handlebar_type_slug") val handlebarTypeSlug: Any,
		@SerializedName("frame_material_slug") val frameMaterialSlug: Any,
		@SerializedName("front_gear_type_slug") val frontGearTypeSlug: Any,
		@SerializedName("rear_gear_type_slug") val rearGearTypeSlug: Any,
		@SerializedName("stolen_record") val stolenRecord: StolenRecord,
		@SerializedName("public_images") val publicImages: List<PublicImage>,
		@SerializedName("components") val components: List<Any>
)