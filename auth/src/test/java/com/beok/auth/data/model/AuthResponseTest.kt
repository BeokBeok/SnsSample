package com.beok.auth.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthResponseTest {

    @Test
    fun `Response를_domain 모델로 변환합니다`() {
        val response = AuthResponse(userId = 9, ok = true)

        val actual = response.toDomain()

        assertThat(actual.userId).isEqualTo(response.userId)
    }
}
