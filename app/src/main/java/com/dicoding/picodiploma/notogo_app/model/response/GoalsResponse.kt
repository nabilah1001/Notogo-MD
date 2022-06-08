package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class GoalsResponse(

	@field:SerializedName("result")
	val result: List<ResultItemGoals>,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class ResultItemGoals(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("goal_id")
	val goalId: Int? = null,

	@field:SerializedName("location_name")
	val locationName: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("done")
	val done: Int? = null,

	@field:SerializedName("location_id")
	val locationId: Int? = null,

	@field:SerializedName("budget")
	val budget: Int? = null
)
