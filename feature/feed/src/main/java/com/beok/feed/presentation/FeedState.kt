package com.beok.feed.presentation

internal sealed class FeedState {

    object Loading : FeedState()

    object Loaded : FeedState()

    data class Error(val throwable: Throwable) : FeedState()
}