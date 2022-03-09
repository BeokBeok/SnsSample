package com.beok.feed.presentation

import com.beok.feed.domain.model.Card

internal sealed class FeedState {

    object Loading : FeedState()

    data class Loaded(val items: List<Card>) : FeedState()

    data class Error(val throwable: Throwable) : FeedState()
}
