package com.beok.auth.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beok.auth.domain.model.Auth
import com.beok.auth.domain.usecase.SignInUseCase
import com.beok.auth.presentation.model.AuthState
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

class AuthViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val signInUseCase: SignInUseCase = mockk(relaxed = true)
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        viewModel = AuthViewModel(signInUseCase = signInUseCase)
    }

    @Test
    fun `닉네임과 비밀번호를 입력하지 않고_로그인하면_EmptyNickname 상태가 된다`() = runBlocking {
        // given

        // when
        viewModel.signIn(nickName = "", password = "")

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(AuthState.EmptyNickname)
    }

    @Test
    fun `닉네임을 입력하고 비밀번호를 입력하지 않고_로그인하면_EmptyPassword 상태가 된다`() = runBlocking {
        // given

        // when
        viewModel.signIn(nickName = "ohouse", password = "")

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(AuthState.EmptyPassword)
    }

    @Test
    fun `유효하지 않은 계정정보를 입력하고_로그인하면_Error 상태가 된다`() = runBlocking {
        // given
        val (nickName, password) = "ohouse" to "pas"
        val throwable = Throwable()
        coEvery {
            signInUseCase.execute(nickname = nickName, password = password)
        } returns Result.failure(throwable)

        // when
        viewModel.signIn(nickName = nickName, password = password)

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(AuthState.Error(throwable))
    }

    @Test
    fun `유효한 계정정보를 입력하고_로그인하면_LogIn 상태가 된다`() = runBlocking {
        // given
        val (nickname, password) = "ohouse" to "pass"
        coEvery {
            signInUseCase.execute(nickname = nickname, password = password)
        } returns Result.success(Auth(userId = 0))

        // when
        viewModel.signIn(nickName = nickname, password = password)

        // then
        assertThat(viewModel.state.getOrAwaitValue()).isEqualTo(AuthState.LogIn)
    }
}