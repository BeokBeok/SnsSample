package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class CheckSignInUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : CheckSignInUseCase {

    override fun execute(): Flow<Boolean> =
        repository.isSignIn()
}
