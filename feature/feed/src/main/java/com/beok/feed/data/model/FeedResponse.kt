package com.beok.feed.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class FeedResponse(

	@Json(name="cards")
	val cards: List<CardsItem>? = null,

	@Json(name="ok")
	val ok: Boolean? = null,

	@Json(name="error_msg")
	val errorMsg: String? = null
)

