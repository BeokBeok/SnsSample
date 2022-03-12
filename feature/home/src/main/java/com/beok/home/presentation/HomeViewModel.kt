package com.beok.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.home.domain.usecase.FetchHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val fetchHomeUseCase: FetchHomeUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> get() = _state

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = HomeState.Error(throwable)
    }

    fun fetchHome(isRefresh: Boolean = false) = viewModelScope.launch(coroutineExceptionHandler) {
        _state.value = if (isRefresh) HomeState.Refreshing else HomeState.Loading
        fetchHomeUseCase.execute()
            .onSuccess {
                _state.value = HomeState.Loaded(it)
            }
            .onFailure {
                _state.value = HomeState.Error(it)
            }
    }
}
