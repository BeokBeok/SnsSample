package com.beok.detail.domain.model

internal data class CardDetail(
    val recommendCards: List<RecommendCard>,
    val user: User,
    val card: Card
)
