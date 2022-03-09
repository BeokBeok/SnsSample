package com.beok.feed.data

import com.beok.feed.data.model.CardsItem
import com.beok.feed.data.remote.FeedAPI
import com.beok.feed.domain.model.Card
import com.beok.feed.domain.repository.FeedRepository
import javax.inject.Inject

internal class FeedRepositoryImpl @Inject constructor(
    private val api: FeedAPI
) : FeedRepository {

    override suspend fun fetchFeed(page: Int): Result<List<Card>> = runCatching {
        val response = api.fetchFeed(page = page)
        if (response.ok == true) {
            response.cards?.map(CardsItem::toDomain) ?: emptyList()
        } else {
            throw Throwable(response.errorMsg)
        }
    }
}
