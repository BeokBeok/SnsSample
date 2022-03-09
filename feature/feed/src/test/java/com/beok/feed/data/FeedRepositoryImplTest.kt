package com.beok.feed.data

import com.beok.feed.data.model.CardsItem
import com.beok.feed.data.model.FeedResponse
import com.beok.feed.data.remote.FeedAPI
import com.beok.feed.domain.repository.FeedRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FeedRepositoryImplTest {

    private val api: FeedAPI = mockk(relaxed = true)
    private lateinit var repository: FeedRepository

    @Before
    fun setup() {
        repository = FeedRepositoryImpl(api = api)
    }

    @Test
    fun `피드 데이터를_불러옵니다`() = runBlocking {
        // given
        val response = FeedResponse(cards = emptyList(), ok = true)
        coEvery {
            api.fetchFeed()
        } returns response

        // when
        val actual = repository.fetchFeed(page = 1)
            .getOrNull()

        // then
        assertThat(actual).isEqualTo(response.cards?.map(CardsItem::toDomain))
    }

    @Test
    fun `유호하지 않은 피드 데이터를 불러오면_에러가 발생합니다`() = runBlocking {
        // given
        val response = FeedResponse(cards = emptyList(), ok = false, errorMsg = "error")
        coEvery {
            api.fetchFeed()
        } returns response

        // when
        val actual = repository.fetchFeed(page = 1).exceptionOrNull()

        // then
        assertThat(actual).hasMessageThat().contains(response.errorMsg)
    }
}
