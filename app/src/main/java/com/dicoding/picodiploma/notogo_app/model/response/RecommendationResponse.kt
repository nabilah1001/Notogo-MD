package com.dicoding.picodiploma.notogo_app.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RecommendationResponse(

	@field:SerializedName("result")
	val result: List<ResultItemRecommendation>,

	@field:SerializedName("error")
	val error: Boolean? = null
)

@Parcelize
data class ResultItemRecommendation(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("category")
	val category: List<String>,

	@field:SerializedName("location_id")
	val locationId: Int? = null
): Parcelable
