package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import javax.inject.Inject

internal class SignInUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignInUseCase {

    override suspend fun execute(nickname: String, password: String): Result<Boolean> =
        repository.signIn(nickname, password)
}
