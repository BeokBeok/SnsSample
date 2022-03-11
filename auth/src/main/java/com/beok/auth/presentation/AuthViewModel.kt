package com.beok.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.auth.domain.usecase.CheckSignInUseCase
import com.beok.auth.domain.usecase.SignInUseCase
import com.beok.auth.presentation.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkSignInUseCase: CheckSignInUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState> get() = _state

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _state.value = AuthState.Error(throwable)
        }

    fun isSignIn() = viewModelScope.launch(coroutineExceptionHandler) {
        checkSignInUseCase.execute()
            .catch { throwable ->
                _state.value = AuthState.Error(throwable)
            }
            .collect {
                if (it) _state.value = AuthState.LogIn
            }
    }

    fun signIn(nickName: String, password: String) {
        if (nickName.isEmpty()) {
            _state.value = AuthState.EmptyNickname
            return
        }
        if (password.isEmpty()) {
            _state.value = AuthState.EmptyPassword
            return
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            signInUseCase.execute(nickname = nickName, password = password)
                .onSuccess {
                    _state.value = AuthState.LogIn
                }
                .onFailure {
                    _state.value = AuthState.Error(it)
                }
        }
    }
}
