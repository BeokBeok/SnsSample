package com.beok.detail.data.remote

import com.beok.detail.data.model.CardDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CardDetailAPI {

    @GET("/cards/{id}")
    suspend fun fetchCardDetail(
        @Path("id") id: Int
    ): CardDetailResponse
}
