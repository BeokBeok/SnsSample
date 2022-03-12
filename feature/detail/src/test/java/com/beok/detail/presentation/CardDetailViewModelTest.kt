package com.beok.detail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beok.detail.domain.model.Card
import com.beok.detail.domain.model.CardDetail
import com.beok.detail.domain.model.User
import com.beok.detail.domain.usecase.FetchCardDetailUseCase
import com.beok.detail.presentation.model.CardDetailState
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

class CardDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fetchCardDetailUseCase: FetchCardDetailUseCase = mockk(relaxed = true)
    private val savedStateHandle: SavedStateHandle = mockk()
    private lateinit var viewModel: CardDetailViewModel

    @Before
    fun setup() {
        viewModel = CardDetailViewModel(
            savedStateHandle = savedStateHandle,
            fetchCardDetailUseCase = fetchCardDetailUseCase
        )
    }

    @Test
    fun `유효한 카드 상세 정보를 불러오면_Loaded 상태입니다`() = runBlocking {
        // given
        val mockResponse = CardDetail(
            recommendCards = emptyList(),
            user = User(nickname = "", id = 0, introduction = ""),
            card = Card(
                userId = 0,
                imgUrl = "",
                description = "",
                id = 0
            )
        )
        coEvery {
            fetchCardDetailUseCase.execute(id = 0)
        } returns Result.success(mockResponse)

        // when
        viewModel.fetchCardDetail(id = 0)

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(CardDetailState.Loaded(mockResponse))
    }

    @Test
    fun `카드 상세 데이터를 불러오기 실패하면_Error 상태입니다`() = runBlocking {
        // given
        val mockResponse = Throwable()
        coEvery {
            fetchCardDetailUseCase.execute(id = 0)
        } returns Result.failure(mockResponse)

        // when
        viewModel.fetchCardDetail(id = 0)

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(CardDetailState.Error(mockResponse))
    }
}
