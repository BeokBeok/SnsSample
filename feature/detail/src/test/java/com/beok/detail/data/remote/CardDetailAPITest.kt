package com.beok.detail.data.remote

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class CardDetailAPITest {

    private lateinit var server: MockWebServer
    private lateinit var api: CardDetailAPI

    @Before
    fun setup() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl(server.url(""))
            .build()
            .create()
    }

    @Test
    fun `카드 상세 정보를_조회합니다`() = runBlocking {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/card_detail.json").readText())
            .setResponseCode(200)
        server.enqueue(response)

        // when
        val actual = api.fetchCardDetail(id = 79)

        // then
        assertThat(actual.card?.userId).isEqualTo(0)
        assertThat(actual.user?.id).isEqualTo(0)
        assertThat(actual.recommendCards?.size).isEqualTo(5)
        assertThat(actual.ok).isTrue()
    }

    @Test
    fun `유호하지 않은 카드 상세 정보를 불러오면_에러가 발생합니다`() = runBlocking {
        // given
        val response = MockResponse().setBody(CARD_DETAIL_INVALIDATE_JSON)
            .setResponseCode(200)
        server.enqueue(response)

        // when
        val actual = api.fetchCardDetail(id = 79)

        // then
        assertThat(actual.errorMsg).isEmpty()
    }

    companion object {
        private const val CARD_DETAIL_INVALIDATE_JSON =
            """
                {"ok":false,"error_msg":""}
            """
    }
}
