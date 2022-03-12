package com.beok.detail.presentation

import com.beok.detail.domain.model.CardDetail

internal sealed class CardDetailState {

    object Loading : CardDetailState()

    object Refreshing : CardDetailState()

    data class Loaded(val item: CardDetail) : CardDetailState()

    data class Error(val throwable: Throwable) : CardDetailState()
}
