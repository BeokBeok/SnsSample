package com.beok.home.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(

	@Json(name="popular_users")
	val popularUsers: List<PopularUsersItem?>? = null,

	@Json(name="popular_cards")
	val popularCards: List<PopularCardsItem?>? = null,

	@Json(name="ok")
	val ok: Boolean? = null
)

