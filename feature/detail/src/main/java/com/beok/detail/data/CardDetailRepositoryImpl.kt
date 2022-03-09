package com.beok.detail.data

import com.beok.detail.data.remote.CardDetailAPI
import com.beok.detail.domain.model.CardDetail
import com.beok.detail.domain.repository.CardDetailRepository

internal class CardDetailRepositoryImpl(
    private val api: CardDetailAPI
) : CardDetailRepository {

    override suspend fun fetchCardDetail(id: Int): Result<CardDetail> = runCatching {
        val response = api.fetchCardDetail(id = id)
        if (response.ok == true) {
            response.toDomain()
        } else {
            throw Throwable(response.errorMsg)
        }
    }
}
