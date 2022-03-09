package com.beok.home.data.remote

import com.beok.home.data.model.HomeResponse
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class HomeAPITest {

    private lateinit var server: MockWebServer
    private lateinit var api: HomeAPI

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
    fun `홈 데이터를 불러옵니다`() = runBlocking {
        // given
        val response = MockResponse()
            .setBody(File("src/test/resources/home.json").readText())
            .setResponseCode(200)
        server.enqueue(response)

        // when
        val actual = api.fetchHome()

        // then
        assertThat(actual.ok).isTrue()
        assertThat(actual.popularCards?.size).isEqualTo(5)
        assertThat(actual.popularUsers?.size).isEqualTo(4)
    }

    @Test
    fun `유호하지 않은 홈 데이터를 불러오면_에러가 발생합니다`() = runBlocking {
        // given
        val response = MockResponse().setBody(HOME_INVALIDATE_JSON)
            .setResponseCode(200)
        server.enqueue(response)

        // when
        val actual = api.fetchHome()

        // then
        assertThat(actual.error_msg).isEmpty()
    }

    companion object {
        private const val HOME_INVALIDATE_JSON =
            """
                {"ok":false,"error_msg":""}
            """
    }
}
