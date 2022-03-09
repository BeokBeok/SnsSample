package com.beok.home.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularCardsItem(

	@Json(name="user_id")
	val userId: Int? = null,

	@Json(name="img_url")
	val imgUrl: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="id")
	val id: Int? = null
)
