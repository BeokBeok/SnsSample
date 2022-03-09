package com.beok.home.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeResponseTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val response = HomeResponse(
            popularUsers = emptyList(),
            popularCards = emptyList(),
            ok = true
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.popularCards).isEmpty()
        assertThat(actual.popularUsers).isEmpty()
    }
}
