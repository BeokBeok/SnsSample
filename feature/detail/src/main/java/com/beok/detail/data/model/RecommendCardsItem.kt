package com.beok.detail.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RecommendCardsItem(

	@Json(name="user_id")
	val userId: Int? = null,

	@Json(name="img_url")
	val imgUrl: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="id")
	val id: Int? = null
)
