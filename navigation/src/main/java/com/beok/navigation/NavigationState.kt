package com.beok.navigation

sealed class NavigationState {

    data class CardDetail(val id: Int) : NavigationState()

    object Auth : NavigationState()
}
