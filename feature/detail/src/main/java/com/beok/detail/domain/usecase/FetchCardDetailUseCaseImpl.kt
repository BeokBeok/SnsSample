package com.beok.detail.domain.usecase

import com.beok.detail.domain.model.CardDetail
import com.beok.detail.domain.repository.CardDetailRepository

internal class FetchCardDetailUseCaseImpl(
    private val repository: CardDetailRepository
) : FetchCardDetailUseCase {

    override suspend fun execute(id: Int): Result<CardDetail> =
        repository.fetchCardDetail(id = id)
}
