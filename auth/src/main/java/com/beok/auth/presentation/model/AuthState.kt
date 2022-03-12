package com.beok.auth.presentation.model

sealed class AuthState {

    object LogIn : AuthState()

    object NotLogIn : AuthState()

    object EmptyNickname : AuthState()

    object EmptyPassword : AuthState()

    data class Error(val throwable: Throwable) : AuthState()
}
