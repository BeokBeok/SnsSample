package com.beok.auth.data

import com.beok.auth.data.model.AuthRequest
import com.beok.auth.data.remote.AuthAPI
import com.beok.auth.domain.repository.AuthRepository
import com.beok.auth.domain.model.Auth
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI
) : AuthRepository {

    override suspend fun signIn(nickName: String, password: String): Result<Auth> = runCatching {
        val response = api.signIn(
            authRequest = AuthRequest(nickName = nickName, pwd = password)
        )
        if (response.ok == true) {
            response.toDomain()
        } else {
            throw Throwable(response.error_msg)
        }
    }
}
