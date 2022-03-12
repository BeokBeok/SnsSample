package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignOutUseCaseImplTest {

    private val repository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: SignOutUseCase

    @Before
    fun setup() {
        useCase = SignOutUseCaseImpl(repository = repository)
    }

    @Test
    fun `로그아웃_합니다`() = runBlocking {
        // given

        // when
        useCase.execute()

        // then
        coVerify {
            repository.signOut()
        }
    }
}
