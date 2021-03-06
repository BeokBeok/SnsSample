package com.beok.home.presentation.model

import com.beok.home.domain.model.Home

internal sealed class HomeState {

    object Loading : HomeState()

    object Refreshing : HomeState()

    data class Loaded(val items: Home) : HomeState()

    data class Error(val throwable: Throwable) : HomeState()
}
