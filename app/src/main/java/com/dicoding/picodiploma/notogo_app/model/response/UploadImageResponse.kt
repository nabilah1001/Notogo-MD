package com.dicoding.picodiploma.notogo_app.model.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
