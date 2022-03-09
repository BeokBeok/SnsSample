package com.beok.auth.domain.usecase

import com.beok.auth.domain.model.Auth
import com.beok.auth.domain.repository.AuthRepository

internal class SignInUseCaseImpl(
    private val repository: AuthRepository
) : SignInUseCase {

    override suspend fun execute(nickname: String, password: String): Result<Auth> =
        repository.signIn(nickname, password)
}
