package com.dicoding.picodiploma.notogo_app.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FavoriteResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>,

	@field:SerializedName("error")
	val error: Boolean? = null
)

@Parcelize
data class ResultItem(
	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("category")
	val category: List<String>,

	@field:SerializedName("location_id")
	val locationId: Int? = null

): Parcelable
