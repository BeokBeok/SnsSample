package com.beok.auth.domain.repository

import com.beok.auth.domain.model.Auth

internal interface AuthRepository {
    suspend fun signIn(nickName: String, password: String): Result<Auth>
}
