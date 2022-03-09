package com.beok.auth.data

import com.beok.auth.data.model.AuthRequest
import com.beok.auth.data.model.AuthResponse
import com.beok.auth.data.remote.AuthAPI
import com.beok.auth.domain.repository.AuthRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthRepositoryImplTest {

    private val api: AuthAPI = mockk(relaxed = true)
    private lateinit var repository: AuthRepository

    @Before
    fun setup() {
        repository = AuthRepositoryImpl(api = api)
    }

    @Test
    fun `아이디와 일치하는 비밀번호로_로그인하면_UserID를 반환합니다`() = runBlocking {
        // given
        val (nickName, password) = "ohouse" to "pass"
        val mockResponse = AuthResponse(userId = 9, ok = true)
        coEvery {
            api.signIn(authRequest = AuthRequest(nickName = nickName, pwd = password))
        } returns mockResponse

        // when
        val actual = repository.signIn(nickName = nickName, password = password)
            .getOrNull()

        // then
        assertThat(actual?.userId).isEqualTo(mockResponse.userId)
    }

    @Test
    fun `아이디와 일치하지 않는 비밀번호로_로그인하면_에러가 발생합니다`() = runBlocking {
        // given
        val (nickName, password) = "ohouse" to "pas"
        val errorMsg = "비밀번호가 틀립니다."
        coEvery {
            api.signIn(authRequest = AuthRequest(nickName = nickName, pwd = password))
        } throws Throwable(errorMsg)

        // when
        val actual = repository.signIn(nickName = nickName, password = password)
            .exceptionOrNull()

        // then
        assertThat(actual).hasMessageThat()
            .contains(errorMsg)
    }

    @Test
    fun `유효하지 않는 아이디로_로그인하면_에러가 발생합니다`() = runBlocking {
        // given
        val (nickName, password) = "ohous" to "pass"
        val errorMsg = "유저가 없습니다."
        coEvery {
            api.signIn(authRequest = AuthRequest(nickName = nickName, pwd = password))
        } throws Throwable(errorMsg)

        // when
        val actual = repository.signIn(nickName = nickName, password = password)
            .exceptionOrNull()

        // then
        assertThat(actual).hasMessageThat()
            .contains(errorMsg)
    }

}