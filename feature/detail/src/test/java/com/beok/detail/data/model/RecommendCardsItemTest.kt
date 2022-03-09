package com.beok.detail.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecommendCardsItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val response = RecommendCardsItem(
            userId = 0,
            imgUrl = "https://farm66.staticflickr.com/65535/51663420094_8754560c57.jpg",
            description = "Boys Cockfighting, Misericord, Westminster Abbey",
            id = 53
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.userId).isEqualTo(response.userId)
        assertThat(actual.imgUrl).isEqualTo(response.imgUrl)
        assertThat(actual.description).isEqualTo(response.description)
        assertThat(actual.id).isEqualTo(response.id)
    }
}
