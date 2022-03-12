package com.beok.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beok.home.domain.model.Home
import com.beok.home.domain.usecase.FetchHomeUseCase
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

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fetchHomeUseCase: FetchHomeUseCase = mockk(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(fetchHomeUseCase = fetchHomeUseCase)
    }

    @Test
    fun `유호한 홈 데이터를 불러오면_Loaded 상태입니다`() = runBlocking {
        // given
        val mockResponse = Home(popularUsers = emptyList(), popularCards = emptyList())
        coEvery {
            fetchHomeUseCase.execute()
        } returns Result.success(mockResponse)

        // when
        viewModel.fetchHome()

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(HomeState.Loaded(mockResponse))
    }

    @Test
    fun `홈 데이터를 불러오기 실패하면_Error 상태입니다`() = runBlocking {
        // given
        val mockResponse = Throwable()
        coEvery {
            fetchHomeUseCase.execute()
        } returns Result.failure(mockResponse)

        // when
        viewModel.fetchHome()

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(HomeState.Error(mockResponse))
    }
}
