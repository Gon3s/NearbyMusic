package com.gones.nearbymusic.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gones.nearbymusic.domain.repository.UserRepository
import com.gones.nearbymusic.ui.register.RegisterViewModel
import com.gones.nearbymusic.util.Resource
import com.gones.nearbymusic.util.isValidEmail
import com.gones.nearbymusic.util.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun login() {
        viewModelScope.launch {
            if (!email.isValidEmail()) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = "L'email n'est pas valide"
                    )
                )
                return@launch
            }

            if (password.isBlank()) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = "Le mot de passe est vide"
                    )
                )
                return@launch
            }

            userRepository.login(email, password).collect() {
                when(it) {
                    is Resource.Success -> {
                        _eventFlow.emit(UiEvent.LoginOk)
                    }

                    is Resource.Error -> {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = it.message ?: "Connexion impossible"
                            )
                        )
                    }

                    else -> {

                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object LoginOk: UiEvent()
    }
}