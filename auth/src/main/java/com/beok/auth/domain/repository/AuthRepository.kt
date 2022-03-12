package com.beok.auth.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AuthRepository {
    fun isSignIn(): Flow<Boolean>
    suspend fun signIn(nickName: String, password: String): Result<Boolean>
    suspend fun signOut(): Result<Unit>
}
