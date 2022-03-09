package com.beok.home.domain.usecase

import com.beok.home.domain.repository.HomeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchHomeUseCaseImplTest {

    private val repository: HomeRepository = mockk(relaxed = true)
    private lateinit var useCase: FetchHomeUseCase

    @Before
    fun setup() {
        useCase = FetchHomeUseCaseImpl(repository = repository)
    }

    @Test
    fun `홈 데이터를_불러옵니다`() = runBlocking {
        // given

        // when
        useCase.execute()

        // then
        coVerify {
            repository.fetchHome()
        }
    }
}
