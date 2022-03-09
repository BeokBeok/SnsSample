package com.beok.home.data.remote

import com.beok.home.data.model.HomeResponse
import retrofit2.http.GET

internal interface HomeAPI {

    @GET("/home")
    suspend fun fetchHome(): HomeResponse
}
