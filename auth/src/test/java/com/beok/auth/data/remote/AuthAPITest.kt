package com.beok.auth.data.remote

import com.beok.auth.data.model.AuthRequest
import com.beok.auth.data.model.AuthResponse
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class AuthAPITest {

    private lateinit var server: MockWebServer
    private lateinit var api: AuthAPI

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
    fun `존재하는 아이디와 일치하는 비밀번호로_로그인하면_UserID를 얻습니다`() = runBlocking {
        // given
        val request = AuthRequest(nickname = "ohouse", password = "pass")
        val response = MockResponse().setBody(ID_PW_SUCCESS_JSON)
            .setResponseCode(200)
        val expected = AuthResponse(userId = 9, ok = true)
        server.enqueue(response)

        // when
        val actual = api.signIn(authRequest = request)

        // then
        assertThat(actual.userId).isEqualTo(expected.userId)
    }

    @Test
    fun `존재하지 않는 아이디로_로그인하면_유저가 없습니다 에러 메시지를 받습니다`() = runBlocking {
        // given
        val request = AuthRequest(nickname = "aa", password = "")
        val response = MockResponse().setBody(INVALID_ID_JSON)
            .setResponseCode(200)
        val expected = AuthResponse(ok = false, error_msg = "유저가 없습니다.")
        server.enqueue(response)

        // when
        val actual = api.signIn(authRequest = request)

        // then
        assertThat(actual.error_msg).isEqualTo(expected.error_msg)
    }

    @Test
    fun `존재하는 아이디와 일치하지 않는 비밀번호로_로그인하면_비밀번호가 틀립니다 에러 메시지를 받습니다`() = runBlocking {
        // given
        val request = AuthRequest(nickname = "ohouse", password = "pas")
        val response = MockResponse().setBody(INVALID_PW_JSON)
            .setResponseCode(200)
        val expected = AuthResponse(ok = false, error_msg = "비밀번호가 틀립니다.")
        server.enqueue(response)

        // when
        val actual = api.signIn(authRequest = request)

        // then
        assertThat(actual.error_msg).isEqualTo(expected.error_msg)
    }

    companion object {
        private const val ID_PW_SUCCESS_JSON =
            """
                {"ok":true,"user_id":9,"error_msg":null}
            """
        private const val INVALID_ID_JSON =
            """
                {"ok":false,"error_msg":"유저가 없습니다."}
            """
        private const val INVALID_PW_JSON =
            """
                {"ok":false,"error_msg":"비밀번호가 틀립니다."}
            """
    }
}
