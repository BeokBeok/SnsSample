package com.beok.detail.data.model

import com.beok.detail.domain.model.Card
import com.beok.detail.domain.model.CardDetail
import com.beok.detail.domain.model.User
import com.beok.shared.mapper.DataToDomainMapper
import com.beok.shared.mapper.toDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CardDetailResponse(

    @Json(name = "recommend_cards")
    val recommendCards: List<RecommendCardsItem>? = null,

    @Json(name = "ok")
    val ok: Boolean? = null,

    @Json(name = "user")
    val user: UsersItem? = null,

    @Json(name = "card")
    val card: CardsItem? = null,

    @Json(name = "error_msg")
    val errorMsg: String? = null
) : DataToDomainMapper<CardDetail> {

    override fun toDomain(): CardDetail = CardDetail(
        recommendCards = recommendCards?.toDomain() ?: emptyList(),
        user = user?.toDomain() ?: User(nickname = "", id = -1, introduction = ""),
        card = card?.toDomain() ?: Card(userId = -1, imgUrl = "", description = "", id = -1)
    )
}
