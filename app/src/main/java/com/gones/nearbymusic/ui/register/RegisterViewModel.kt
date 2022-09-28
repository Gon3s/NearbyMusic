package com.gones.nearbymusic.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gones.nearbymusic.domain.repository.UserRepository
import com.gones.nearbymusic.util.Resource
import com.gones.nearbymusic.util.isValidEmail
import com.gones.nearbymusic.util.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var uiState = mutableStateOf(RegisterUiState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val name get() = uiState.value.name
    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue.trim())
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue.trim())
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue.trim())
    }

    fun register() {
        viewModelScope.launch {
            if (email.isBlank()) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = "Le nom doit être renseigné"
                    )
                )
                return@launch
            }

            if (!email.isValidEmail()) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = "L'email n'est pas valide"
                    )
                )
                return@launch
            }

            if (!password.isValidPassword()) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = "Le mot de passe doit avoir 8 caractères minimum"
                    )
                )
                return@launch
            }

            userRepository.register(email, password, name).collect() {
                when(it) {
                    is Resource.Loading -> {
                        _eventFlow.emit(UiEvent.Loading)
                    }

                    is Resource.Success -> {
                        _eventFlow.emit(UiEvent.RegisterOk)
                    }

                    is Resource.Error -> {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = it.message ?: "Couldn't create account"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object Loading: UiEvent()
        data class ShowSnackbar(val message: String): UiEvent()
        object RegisterOk: UiEvent()
    }
}
