package com.beok.auth.domain.usecase

import com.beok.auth.domain.model.Auth

interface SignInUseCase {
    suspend fun execute(nickname: String, password: String): Result<Auth>
}
