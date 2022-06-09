package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("result")
	val result: ArrayList<ItemLocation>,

	@field:SerializedName("error")
	val error: Boolean
)

data class ItemLocation(

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("location_id")
	val locationId: String
)
