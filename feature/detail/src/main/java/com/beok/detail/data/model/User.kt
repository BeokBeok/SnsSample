package com.beok.detail.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class User(

	@Json(name="nickname")
	val nickname: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="introduction")
	val introduction: String? = null
)
