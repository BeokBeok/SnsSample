package com.beok.home.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PopularUsersItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val nickname = "aaaa"
        val id = 0
        val introduction = "a소개합니다."
        val response = PopularUsersItem(
            nickname = nickname,
            id = id,
            introduction = introduction
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.nickname).isEqualTo(nickname)
        assertThat(actual.id).isEqualTo(id)
        assertThat(actual.introduction).isEqualTo(introduction)
    }
}
