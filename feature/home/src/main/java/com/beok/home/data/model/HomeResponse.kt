package com.beok.home.data.model

import com.beok.home.domain.model.Home
import com.beok.shared.mapper.DataToDomainMapper
import com.beok.shared.mapper.toDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class HomeResponse(

	@Json(name="popular_users")
	val popularUsers: List<PopularUsersItem>? = null,

	@Json(name="popular_cards")
	val popularCards: List<PopularCardsItem>? = null,

	@Json(name="ok")
	val ok: Boolean? = null,

	@Json(name = "error_msg")
	val errorMsg: String? = null
) : DataToDomainMapper<Home> {

	override fun toDomain(): Home = Home(
		popularUsers = popularUsers?.toDomain() ?: emptyList(),
		popularCards = popularCards?.toDomain() ?: emptyList()
	)
}

