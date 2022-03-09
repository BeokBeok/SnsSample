package com.beok.auth.domain.repository

import com.beok.auth.domain.model.Auth

interface AuthRepository {
    suspend fun signIn(nickName: String, password: String): Result<Auth>
}
