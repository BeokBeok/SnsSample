package com.beok.detail.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UsersItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val response = UsersItem(
            nickname = "aaaa",
            id = 0,
            introduction = "a소개합니다"
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.nickname).isEqualTo(response.nickname)
        assertThat(actual.id).isEqualTo(response.id)
        assertThat(actual.introduction).isEqualTo(response.introduction)
    }
}
