package com.beok.auth.domain.usecase

import com.beok.auth.domain.repository.AuthRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CheckSignInUseCaseImplTest {

    private val repository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: CheckSignInUseCase

    @Before
    fun setup() {
        useCase = CheckSignInUseCaseImpl(repository = repository)
    }

    @Test
    fun `로그인 여부를_확인합니다`() {
        // given

        // when
        useCase.execute()

        // then
        verify {
            repository.isSignIn()
        }
    }
}
