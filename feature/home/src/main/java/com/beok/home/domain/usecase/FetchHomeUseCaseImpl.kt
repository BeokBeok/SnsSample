package com.beok.home.domain.usecase

import com.beok.home.domain.model.Home
import com.beok.home.domain.repository.HomeRepository

internal class FetchHomeUseCaseImpl(
    private val repository: HomeRepository
) : FetchHomeUseCase {

    override suspend fun execute(): Result<Home> =
        repository.fetchHome()
}
