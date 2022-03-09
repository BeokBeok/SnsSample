package com.beok.home.domain.usecase

import com.beok.home.domain.model.Home

internal interface FetchHomeUseCase {
    suspend fun execute(): Result<Home>
}
