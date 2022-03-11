package com.beok.auth.domain.usecase

import kotlinx.coroutines.flow.Flow

interface CheckSignInUseCase {
    fun execute(): Flow<Boolean>
}
