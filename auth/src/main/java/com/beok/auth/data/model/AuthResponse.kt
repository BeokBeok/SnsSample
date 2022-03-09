package com.beok.auth.data.model

import com.beok.shared.mapper.DataToDomainMapper
import com.beok.auth.domain.model.Auth
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
) : DataToDomainMapper<Auth> {

    override fun toDomain(): Auth = Auth(userId = userId ?: -1)
}
