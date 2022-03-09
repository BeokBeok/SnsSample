package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignInUseCaseImplTest {

    private val repository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: SignInUseCase

    @Before
    fun setup() {
        useCase = SignInUseCaseImpl(repository = repository)
    }

    @Test
    fun `유저가 로그인합니다`() = runBlocking {
        // given
        val (nickName, password) = "ohouse" to "pass"

        // when
        useCase.execute(nickname = nickName, password = password)

        // then
        coVerify {
            repository.signIn(nickName = nickName, password = password)
        }
    }
}
