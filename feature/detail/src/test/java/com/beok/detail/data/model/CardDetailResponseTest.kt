package com.beok.detail.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CardDetailResponseTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val response = CardDetailResponse(
            recommendCards = emptyList(),
            ok = true,
            user = UsersItem(
                nickname = "aaaa",
                id = 0,
                introduction = "a소개합니다"
            ),
            card = CardsItem(
                userId = 0,
                imgUrl = "https://farm66.staticflickr.com/65535/51661871617_825cddcc88.jpg",
                description = "Monochrome, Voronezh, Russian Federation.",
                id = 79
            ),
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.recommendCards).isEqualTo(response.recommendCards)
        assertThat(actual.card.id).isEqualTo(response.card?.id)
        assertThat(actual.user.id).isEqualTo(response.user?.id)
    }
}
