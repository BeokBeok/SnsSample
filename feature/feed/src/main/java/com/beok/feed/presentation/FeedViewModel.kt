package com.beok.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.feed.domain.model.Card
import com.beok.feed.domain.usecase.FetchFeedUseCase
import com.beok.feed.presentation.model.PageInfo
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

    private val _feed = MutableLiveData<List<Card>>()
    val feed: LiveData<List<Card>> get() = _feed

    private val pageInfo = PageInfo()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = FeedState.Error(throwable)
    }

    fun fetchFeed(isNext: Boolean = false, isRefresh: Boolean = false) {
        pageInfo.setup(isNext = isNext)
        if (pageInfo.isEnd) return

        _state.value = if (isRefresh) FeedState.Refreshing else FeedState.Loading
        if (!isNext) _feed.value = emptyList()

        viewModelScope.launch(coroutineExceptionHandler) {
            fetchFeedUseCase.execute(page = pageInfo.value)
                .onSuccess {
                    _state.value = FeedState.Loaded
                    _feed.value = (_feed.value?.toList() ?: emptyList())
                        .plus(it)
                    pageInfo.update(isEnd = it.isEmpty())
                }
                .onFailure {
                    _state.value = FeedState.Error(it)
                }
        }
    }
}
