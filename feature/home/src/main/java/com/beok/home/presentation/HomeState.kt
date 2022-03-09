package com.beok.home.presentation

import com.beok.home.domain.model.Home

internal sealed class HomeState {

    object Loading : HomeState()

    data class Loaded(val items: Home) : HomeState()

    data class Error(val throwable: Throwable) : HomeState()

    data class CardClick(val id: Int) : HomeState()
}
