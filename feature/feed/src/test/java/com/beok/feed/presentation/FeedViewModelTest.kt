package com.beok.feed.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beok.feed.domain.model.Card
import com.beok.feed.domain.usecase.FetchFeedUseCase
import com.beok.shared.test.MainCoroutineRule
import com.beok.shared.test.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class FeedViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fetchFeedUseCase: FetchFeedUseCase = mockk(relaxed = true)
    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        viewModel = FeedViewModel(fetchFeedUseCase = fetchFeedUseCase)
    }

    @Test
    fun `유효한 피드 데이터를 불러오면_Loaded 상태입니다`() = runBlocking {
        // given
        val mockResponse = listOf(Card(userId = 0, imgUrl = "", description = "", id = 0))
        coEvery {
            fetchFeedUseCase.execute()
        } returns Result.success(mockResponse)

        // when
        viewModel.fetchFeed()

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(FeedState.Loaded)
        assertThat(viewModel.feed.getOrAwaitValue()).isEqualTo(mockResponse)
    }

    @Test
    fun `피드 데이터를 불러오기 싪패하면_Error 상태입니다`() = runBlocking {
        // given
        val mockResponse = Throwable()
        coEvery {
            fetchFeedUseCase.execute()
        } returns Result.failure(mockResponse)

        // when
        viewModel.fetchFeed()

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(FeedState.Error(mockResponse))
    }
}
