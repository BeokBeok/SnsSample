package com.beok.detail.domain.repository

import com.beok.detail.domain.model.CardDetail

internal interface CardDetailRepository {
    suspend fun fetchCardDetail(id: Int): Result<CardDetail>
}
