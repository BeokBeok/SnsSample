package com.beok.navigation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beok.shared.test.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class NavigationViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NavigationViewModel

    @Before
    fun setup() {
        viewModel = NavigationViewModel()
    }

    @Test
    fun `카드 상세 화면으로_이동합니다`() {
        // given

        // when
        viewModel.navigate(navigationState = NavigationState.CardDetail(id = 0))

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(NavigationState.CardDetail(id = 0))
    }

    @Test
    fun `로그인 화면으로_이동합니다`() {
        // given

        // when
        viewModel.navigate(navigationState = NavigationState.Auth)

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(NavigationState.Auth)
    }
}
