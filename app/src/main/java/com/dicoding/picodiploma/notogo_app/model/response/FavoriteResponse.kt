package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class FavoriteResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class ResultItem(

	@field:SerializedName("location")
	val location: String? = null
)
