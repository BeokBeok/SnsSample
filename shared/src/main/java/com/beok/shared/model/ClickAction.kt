package com.beok.shared.model

data class ClickAction<T>(
    val bindingID: Int,
    val action: (item: T) -> Unit
)
