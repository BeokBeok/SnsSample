package com.beok.home.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PopularCardsItemTest {

    @Test
    fun `Response를_Domain 모델로 변환합니다`() {
        // given
        val userId = 0
        val imageUrl = "https://farm66.staticflickr.com/65535/51663869974_8bb818b916.jpg"
        val description = "-"
        val id = 0
        val response = PopularCardsItem(
            userId = userId,
            imgUrl = imageUrl,
            description = description,
            id = id
        )

        // when
        val actual = response.toDomain()

        // then
        assertThat(actual.userId).isEqualTo(userId)
        assertThat(actual.imgUrl).isEqualTo(imageUrl)
        assertThat(actual.description).isEqualTo(description)
        assertThat(actual.id).isEqualTo(id)
    }
}
