package com.beok.home.data.model

import com.beok.home.domain.model.PopularUser
import com.beok.shared.mapper.DataToDomainMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularUsersItem(

	@Json(name="nickname")
	val nickname: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="introduction")
	val introduction: String? = null
) : DataToDomainMapper<PopularUser> {

	override fun toDomain(): PopularUser = PopularUser(
		nickname = nickname ?: "",
		id = id ?: -1,
		introduction = introduction ?: ""
	)
}
