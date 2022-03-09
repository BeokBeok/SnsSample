package com.beok.home.domain.repository

import com.beok.home.domain.model.Home

internal interface HomeRepository {
    suspend fun fetchHome(): Result<Home>
}
