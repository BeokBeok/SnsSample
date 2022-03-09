package com.beok.detail.data

import com.beok.detail.data.model.CardDetailResponse
import com.beok.detail.data.model.CardsItem
import com.beok.detail.data.model.UsersItem
import com.beok.detail.data.remote.CardDetailAPI
import com.beok.detail.domain.repository.CardDetailRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CardDetailRepositoryImplTest {

    private val api: CardDetailAPI = mockk(relaxed = true)
    private lateinit var repository: CardDetailRepository

    @Before
    fun setup() {
        repository = CardDetailRepositoryImpl(api = api)
    }

    @Test
    fun `카드 상세 데이터를_불러옵니다`() = runBlocking {
        // given
        val mockResponse = CardDetailResponse(
            recommendCards = emptyList(),
            ok = true,
            user = UsersItem(nickname = "aaaa", id = 0, introduction = "a소개합니다"),
            card = CardsItem(userId = 0, imgUrl = "", description = "", id = 89),
        )
        coEvery {
            api.fetchCardDetail(id = 0)
        } returns mockResponse

        // when
        val actual = repository.fetchCardDetail(id = 0)
            .getOrNull()

        // then
        assertThat(actual).isEqualTo(mockResponse.toDomain())
    }

    @Test
    fun `유효하지 않은 카드 상세 데이터를 불러오면_에러가 발생합니다`() = runBlocking {
        // given
        val mockResponse = CardDetailResponse(
            recommendCards = emptyList(),
            ok = false,
            user = UsersItem(nickname = "", id = -1, introduction = ""),
            card = CardsItem(userId = -1, imgUrl = "", description = "", id = -1),
            errorMsg = "error"
        )
        coEvery {
            api.fetchCardDetail(id = 0)
        } returns mockResponse

        // when
        val actual = repository.fetchCardDetail(id = 0).exceptionOrNull()

        // then
        assertThat(actual).hasMessageThat().contains("error")
    }
}
