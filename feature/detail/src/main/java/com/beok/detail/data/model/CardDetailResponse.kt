package com.beok.detail.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CardDetailResponse(

	@Json(name="recommend_cards")
	val recommendCards: List<RecommendCardsItem>? = null,

	@Json(name="ok")
	val ok: Boolean? = null,

	@Json(name="user")
	val user: User? = null,

	@Json(name="card")
	val card: Card? = null,

	@Json(name="error_msg")
	val errorMsg: String? = null
)
