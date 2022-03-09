package com.beok.feed.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CardsItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val userId = 0
        val imgUrl = "https://farm66.staticflickr.com/65535/51662906503_4e44bb2b97.jpg"
        val description = "Monochrome, Voronezh, Russian Federation"
        val id = 82
        val response = CardsItem(
            userId = userId,
            imgUrl = imgUrl,
            description = description,
            id = id
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.userId).isEqualTo(userId)
        assertThat(actual.imgUrl).isEqualTo(imgUrl)
        assertThat(actual.description).isEqualTo(description)
        assertThat(actual.id).isEqualTo(id)
    }
}
