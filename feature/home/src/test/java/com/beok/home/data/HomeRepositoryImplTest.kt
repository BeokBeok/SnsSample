package com.beok.home.data

import com.beok.home.data.model.HomeResponse
import com.beok.home.data.remote.HomeAPI
import com.beok.home.domain.repository.HomeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class HomeRepositoryImplTest {

    private val api: HomeAPI = mockk(relaxed = true)
    private lateinit var repository: HomeRepository

    @Before
    fun setup() {
        repository = HomeRepositoryImpl(api = api)
    }

    @Test
    fun `홈 데이터를_불러옵니다`() = runBlocking {
        // given
        val mockResponse = HomeResponse(
            popularUsers = emptyList(),
            popularCards = emptyList(),
            ok = true
        )
        coEvery {
            api.fetchHome()
        } returns mockResponse

        // when
        val actual = repository.fetchHome()
            .getOrNull()

        // then
        assertThat(actual).isEqualTo(mockResponse.toDomain())
    }

    @Test
    fun `유효하지 않은 홈 데이터를_불러오면_에러가 발생합니다`() = runBlocking {
        // given
        val mockResponse = HomeResponse(
            popularUsers = emptyList(),
            popularCards = emptyList(),
            ok = false
        )
        coEvery {
            api.fetchHome()
        } returns mockResponse

        // when
        val actual = repository.fetchHome().exceptionOrNull()

        // then
        assertThat(actual).hasMessageThat().contains("response failed")
    }
}
