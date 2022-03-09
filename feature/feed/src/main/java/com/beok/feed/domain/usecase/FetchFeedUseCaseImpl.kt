package com.beok.feed.domain.usecase

import com.beok.feed.domain.model.Card
import com.beok.feed.domain.repository.FeedRepository

internal class FetchFeedUseCaseImpl(
    private val repository: FeedRepository
) : FetchFeedUseCase {

    override suspend fun fetchFeed(page: Int): Result<List<Card>> =
        repository.fetchFeed(page = page)
}
