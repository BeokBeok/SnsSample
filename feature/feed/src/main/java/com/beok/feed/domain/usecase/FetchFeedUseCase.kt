package com.beok.feed.domain.usecase

import com.beok.feed.domain.model.Card

internal interface FetchFeedUseCase {
    suspend fun execute(page: Int = DEFAULT_PAGE): Result<List<Card>>

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}
