package com.beok.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.detail.domain.usecase.FetchCardDetailUseCase
import com.beok.detail.presentation.model.CardDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
internal class CardDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchCardDetailUseCase: FetchCardDetailUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CardDetailState>()
    val state: LiveData<CardDetailState> get() = _state

    private val cardId by lazy {
        savedStateHandle.get(BUNDLE_KEY_CARD_ID) as? Int ?: -1
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = CardDetailState.Error(throwable)
    }

    fun fetchCardDetail(id: Int = cardId, isRefresh: Boolean = false) {
        _state.value = if (isRefresh) CardDetailState.Refreshing else CardDetailState.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            fetchCardDetailUseCase.execute(id = id)
                .onSuccess {
                    _state.value =  CardDetailState.Loaded(it)
                }
                .onFailure {
                    _state.value = CardDetailState.Error(it)
                }
        }
    }

    companion object {
        private const val BUNDLE_KEY_CARD_ID = "bundle_key_card_id"
    }
}
