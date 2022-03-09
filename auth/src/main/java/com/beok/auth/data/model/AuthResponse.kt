package com.beok.auth.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AuthResponse(
    @Json(name = "user_id")
    val userId: Int? = null,

    @Json(name = "ok")
    val ok: Boolean? = null,

    @Json(name = "error_msg")
    val error_msg: String? = null
)
