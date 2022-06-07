package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class Result(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("history_count")
	val historyCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("favorite_count")
	val favoriteCount: Int? = null,

	@field:SerializedName("goal_count")
	val goalCount: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
