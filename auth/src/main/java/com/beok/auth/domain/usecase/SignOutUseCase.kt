package com.beok.auth.domain.usecase

interface SignOutUseCase {
    suspend fun execute(): Result<Unit>
}
