package com.beok.detail.data.model

import com.beok.detail.domain.model.User
import com.beok.shared.mapper.DataToDomainMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UsersItem(

    @Json(name = "nickname")
    val nickname: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "introduction")
    val introduction: String? = null
) : DataToDomainMapper<User> {

    override fun toDomain(): User = User(
        nickname = nickname ?: "",
        id = id ?: -1,
        introduction = introduction ?: ""
    )
}
