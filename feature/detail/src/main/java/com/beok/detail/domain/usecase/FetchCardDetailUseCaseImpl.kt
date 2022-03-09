package com.beok.detail.domain.usecase

import com.beok.detail.domain.model.CardDetail
import com.beok.detail.domain.repository.CardDetailRepository
import javax.inject.Inject

internal class FetchCardDetailUseCaseImpl @Inject constructor(
    private val repository: CardDetailRepository
) : FetchCardDetailUseCase {

    override suspend fun execute(id: Int): Result<CardDetail> =
        repository.fetchCardDetail(id = id)
}
