package com.beok.auth.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beok.auth.domain.usecase.CheckSignInUseCase
import com.beok.auth.domain.usecase.SignInUseCase
import com.beok.auth.domain.usecase.SignOutUseCase
import com.beok.auth.presentation.model.AuthState
import com.beok.shared.test.MainCoroutineRule
import com.beok.shared.test.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
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
    private val checkSignInUseCase: CheckSignInUseCase = mockk(relaxed = true)
    private val signOutUseCase: SignOutUseCase = mockk(relaxed = true)
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        viewModel = AuthViewModel(
            signInUseCase = signInUseCase,
            checkSignInUseCase = checkSignInUseCase,
            signOutUseCase = signOutUseCase
        )
    }

    @Test
    fun `닉네임과 비밀번호를 입력하지 않고_로그인하면_EmptyNickname 상태가 된다`() = runBlocking {
        // given

        // when
        viewModel.signIn(nickName = "", password = "")

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.EmptyNickname)
    }

    @Test
    fun `닉네임을 입력하고 비밀번호를 입력하지 않고_로그인하면_EmptyPassword 상태가 된다`() = runBlocking {
        // given

        // when
        viewModel.signIn(nickName = "ohouse", password = "")

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.EmptyPassword)
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
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.Error(throwable))
    }

    @Test
    fun `유효한 계정정보를 입력하고_로그인하면_LogIn 상태가 된다`() = runBlocking {
        // given
        val (nickname, password) = "ohouse" to "pass"
        coEvery {
            signInUseCase.execute(nickname = nickname, password = password)
        } returns Result.success(true)
        every {
            checkSignInUseCase.execute()
        } returns flow { emit(true) }

        // when
        viewModel.signIn(nickName = nickname, password = password)
        viewModel.isSignIn()

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.LogIn)
    }

    @Test
    fun `로그인 상태이면_LogIn 상태가 된다`() {
        // given
        every {
            checkSignInUseCase.execute()
        } returns flow { emit(true) }

        // when
        viewModel.isSignIn()

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.LogIn)
    }

    @Test
    fun `로그아웃 상태이면_NotLogIn 상태가 된다`() {
        // given
        every {
            checkSignInUseCase.execute()
        } returns flow { emit(false) }

        // when
        viewModel.isSignIn()

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.NotLogIn)
    }

    @Test
    fun `로그아웃 하면_NotLogIn 상태가 된다`() = runBlocking {
        // given
        coEvery {
            signOutUseCase.execute()
        } returns Result.success(Unit)
        every {
            checkSignInUseCase.execute()
        } returns flow { emit(false) }

        // when
        viewModel.signOut()
        viewModel.isSignIn()

        // then
        assertThat(viewModel.state.getOrAwaitValue().getContentIfNotHandled())
            .isEqualTo(AuthState.NotLogIn)
    }
}
