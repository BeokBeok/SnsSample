package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import javax.inject.Inject

internal class SignOutUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignOutUseCase {

    override suspend fun execute(): Result<Unit> =
        repository.signOut()
}
