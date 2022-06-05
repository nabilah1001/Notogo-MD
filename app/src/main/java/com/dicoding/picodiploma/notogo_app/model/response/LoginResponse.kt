package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)
