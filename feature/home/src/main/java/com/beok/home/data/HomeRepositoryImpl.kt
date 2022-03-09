package com.beok.home.data

import com.beok.home.data.remote.HomeAPI
import com.beok.home.domain.model.Home
import com.beok.home.domain.repository.HomeRepository
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val api: HomeAPI
) : HomeRepository {

    override suspend fun fetchHome(): Result<Home> = runCatching {
        val response = api.fetchHome()
        if (response.ok == true) {
            response.toDomain()
        } else {
            throw Throwable(response.error_msg)
        }
    }
}
