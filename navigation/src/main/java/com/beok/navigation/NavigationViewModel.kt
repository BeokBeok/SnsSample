package com.beok.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beok.shared.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<Event<NavigationState>>()
    val state: LiveData<Event<NavigationState>> get() = _state

    fun navigate(navigationState: NavigationState) {
        _state.value = Event(navigationState)
    }
}
