package com.beok.auth.data

import com.beok.auth.data.local.AuthLocalDataSource
import com.beok.auth.data.model.AuthRequest
import com.beok.auth.data.remote.AuthAPI
import com.beok.auth.domain.repository.AuthRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val api: AuthAPI
) : AuthRepository {

    override fun isSignIn(): Flow<Boolean> =
        localDataSource.isSignIn()

    override suspend fun signIn(nickName: String, password: String): Result<Boolean> = runCatching {
        val response = api.signIn(
            authRequest = AuthRequest(nickname = nickName, password = password)
        )
        if (response.ok != true || response.userId == -1) {
            throw Throwable(response.errorMsg)
        }
        localDataSource.signIn(userID = response.userId ?: -1)
        response.ok
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        localDataSource.signOut()
    }
}
