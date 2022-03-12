package com.beok.feed.presentation.model

internal sealed class FeedState {

    object Loading : FeedState()

    object Refreshing : FeedState()

    object Loaded : FeedState()

    data class Error(val throwable: Throwable) : FeedState()
}
