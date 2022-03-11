package com.beok.auth.domain.usecase

interface SignInUseCase {
    suspend fun execute(nickname: String, password: String): Result<Boolean>
}
