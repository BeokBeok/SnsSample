package com.beok.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.feed.domain.usecase.FetchFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
internal class FeedViewModel @Inject constructor(
    private val fetchFeedUseCase: FetchFeedUseCase
) : ViewModel() {

    private val _state = MutableLiveData<FeedState>()
    val state: LiveData<FeedState> get() = _state

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = FeedState.Error(throwable)
    }

    fun fetchFeed() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            fetchFeedUseCase.execute()
                .onSuccess {
                    _state.value = FeedState.Loaded(it)
                }
                .onFailure {
                    _state.value = FeedState.Error(it)
                }
        }
    }
}
