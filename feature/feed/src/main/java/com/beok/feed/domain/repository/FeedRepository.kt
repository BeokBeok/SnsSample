package com.beok.feed.domain.repository

import com.beok.feed.domain.model.Card

internal interface FeedRepository {
    suspend fun fetchFeed(page: Int): Result<List<Card>>
}
