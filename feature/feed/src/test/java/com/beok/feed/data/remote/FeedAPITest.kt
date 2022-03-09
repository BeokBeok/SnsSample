package com.beok.feed.data.remote

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

class FeedAPITest {

    private lateinit var server: MockWebServer
    private lateinit var api: FeedAPI

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
    fun `피드 데이터를_불러옵니다`() = runBlocking {
        // given
        val response = MockResponse().setBody(File(PATH_FEED_JSON).readText())
            .setResponseCode(200)
        server.enqueue(response)

        // when
        val actual = api.fetchFeed()

        // then
        assertThat(actual.cards?.size).isEqualTo(20)
    }

    @Test
    fun `다음 피드 데이터를_불러옵니다`() = runBlocking {
        // given
        val response = MockResponse().setBody(File(PATH_FEED_MORE_JSON).readText())
            .setResponseCode(200)
        server.enqueue(response)

        // whhen
        val actual = api.fetchFeed(page = 2)

        // then
        assertThat(actual.cards?.size).isEqualTo(20)
    }

    companion object {
        private const val PATH_FEED_JSON = "src/test/resources/feed.json"
        private const val PATH_FEED_MORE_JSON = "src/test/resources/feed_more.json"
    }
}
