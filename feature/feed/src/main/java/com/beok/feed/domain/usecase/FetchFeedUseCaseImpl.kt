package com.beok.feed.domain.usecase

import com.beok.feed.domain.model.Card
import com.beok.feed.domain.repository.FeedRepository
import javax.inject.Inject

internal class FetchFeedUseCaseImpl @Inject constructor(
    private val repository: FeedRepository
) : FetchFeedUseCase {

    override suspend fun execute(page: Int): Result<List<Card>> =
        repository.fetchFeed(page = page)
}
