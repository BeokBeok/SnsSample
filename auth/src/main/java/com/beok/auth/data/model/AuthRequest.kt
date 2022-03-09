package com.beok.auth.data.model

import com.squareup.moshi.Json

internal data class AuthRequest(
    @Json(name = "nickname")
    val nickname: String,

    @Json(name = "pwd")
    val password: String
)
