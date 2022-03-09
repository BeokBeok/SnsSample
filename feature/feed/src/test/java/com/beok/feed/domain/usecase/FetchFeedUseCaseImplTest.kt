package com.beok.feed.domain.usecase

import com.beok.feed.domain.repository.FeedRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchFeedUseCaseImplTest {

    private val repository: FeedRepository = mockk(relaxed = true)
    private lateinit var useCase: FetchFeedUseCase

    @Before
    fun setup() {
        useCase = FetchFeedUseCaseImpl(repository = repository)
    }

    @Test
    fun `피드 데이터를_불러옵니다`() = runBlocking{
        // given

        // when
        useCase.fetchFeed()

        // then
        coVerify {
            repository.fetchFeed(page = 1)
        }
    }
}
