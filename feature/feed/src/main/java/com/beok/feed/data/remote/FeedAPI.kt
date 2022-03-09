package com.beok.feed.data.remote

import com.beok.feed.data.model.FeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FeedAPI {

    @GET("/cards")
    suspend fun fetchFeed(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("per")  per: Int = DEFAULT_PER
    ): FeedResponse

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PER = 20
    }
}
