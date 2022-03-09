package com.beok.detail.domain.usecase

import com.beok.detail.domain.repository.CardDetailRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchCardDetailUseCaseImplTest {

    private val repository: CardDetailRepository = mockk(relaxed = true)
    private lateinit var useCase: FetchCardDetailUseCase

    @Before
    fun setup() {
        useCase = FetchCardDetailUseCaseImpl(repository = repository)
    }

    @Test
    fun `카드 상세 정보를_불러옵니다`() = runBlocking {
        // given

        // when
        useCase.execute(id = 0)

        // then
        coVerify {
            repository.fetchCardDetail(id = 0)
        }
    }
}
