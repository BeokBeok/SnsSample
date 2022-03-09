package com.beok.detail.domain.usecase

import com.beok.detail.domain.model.CardDetail

internal interface FetchCardDetailUseCase {
    suspend fun execute(id: Int): Result<CardDetail>
}
