package com.beok.auth.data.local

import kotlinx.coroutines.flow.Flow

internal interface AuthLocalDataSource {
    fun isSignIn(): Flow<Boolean>
    suspend fun signIn(userID: Int)
}
