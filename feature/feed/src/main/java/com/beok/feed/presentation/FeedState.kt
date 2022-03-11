package com.beok.feed.presentation

internal sealed class FeedState {

    object Loading : FeedState()

    object Refreshing : FeedState()

    object Loaded : FeedState()

    data class CardClick(val id: Int) : FeedState()

    data class Error(val throwable: Throwable) : FeedState()
}
