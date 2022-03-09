package com.beok.home.data.model

import com.beok.home.domain.model.PopularCard
import com.beok.shared.mapper.DataToDomainMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularCardsItem(

    @Json(name = "user_id")
    val userId: Int? = null,

    @Json(name = "img_url")
    val imgUrl: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "id")
    val id: Int? = null
) : DataToDomainMapper<PopularCard> {

    override fun toDomain(): PopularCard = PopularCard(
        userId = userId ?: -1,
        imgUrl = imgUrl ?: "",
        description = description ?: "",
        id = id ?: -1
    )
}
