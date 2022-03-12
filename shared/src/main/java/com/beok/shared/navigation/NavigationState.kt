package com.beok.shared.navigation

sealed class NavigationState {

    data class CardDetail(val id: Int) : NavigationState()

    object Auth : NavigationState()
}
