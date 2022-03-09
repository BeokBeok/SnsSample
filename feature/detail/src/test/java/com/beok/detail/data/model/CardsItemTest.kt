package com.beok.detail.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CardsItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val response = CardsItem(
            userId = 0,
            imgUrl = "https://farm66.staticflickr.com/65535/51661871617_825cddcc88.jpg",
            description = "Monochrome, Voronezh, Russian Federation.",
            id = 79
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.userId).isEqualTo(response.userId)
        assertThat(actual.id).isEqualTo(response.id)
        assertThat(actual.imgUrl).isEqualTo(response.imgUrl)
        assertThat(actual.description).isEqualTo(response.description)
    }
}
