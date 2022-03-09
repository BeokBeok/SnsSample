package com.beok.auth.data.remote

import com.beok.auth.data.model.AuthRequest
import com.beok.auth.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthAPI {

    @POST("/users/sign_in")
    suspend fun signIn(@Body authRequest: AuthRequest): AuthResponse
}
