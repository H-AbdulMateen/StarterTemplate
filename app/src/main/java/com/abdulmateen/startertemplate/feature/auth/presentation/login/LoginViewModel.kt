package com.abdulmateen.startertemplate.feature.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.startertemplate.common.utils.Validator
import com.abdulmateen.startertemplate.feature.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _uiState = mutableStateOf(LoginUIState())
    val uiState: State<LoginUIState> = _uiState

    val apiEvents = MutableSharedFlow<LoginApiEvents>()

    fun onUIEvent(uiEvents: LoginUIEvents) {
        when (uiEvents) {
            is LoginUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(
                    email = uiEvents.email
                )
            }

            is LoginUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(
                    password = uiEvents.password
                )
            }

            LoginUIEvents.OnLoginClick -> {
                validateFields()
            }

            is LoginUIEvents.LoadingStatusChanged -> {
                _uiState.value = _uiState.value.copy(
                    loading = uiEvents.isLoading
                )
            }

            is LoginUIEvents.UpdatePasswordVisibilityStatus -> {
                _uiState.value = _uiState.value.copy(
                    passwordVisibility = !uiState.value.passwordVisibility
                )
            }

            is LoginUIEvents.UpdateEmailErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasEmailError = uiEvents.hasError,
                    emailError = uiEvents.message
                )
            }

            is LoginUIEvents.UpdatePasswordErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasPasswordError = uiEvents.hasError,
                    passwordError = uiEvents.message
                )
            }
        }
    }

    private fun validateFields() {
        val validateEmail = Validator.validateEmail(uiState.value.email)
        onUIEvent(
            LoginUIEvents.UpdateEmailErrorStatus(
                hasError = validateEmail.status,
                message = validateEmail.errorMessage
            )
        )
        if (validateEmail.status)
            return

        val validatePassword = Validator.validatePassword(uiState.value.password)
        onUIEvent(
            LoginUIEvents.UpdatePasswordErrorStatus(
                hasError = validatePassword.status,
                message = validatePassword.errorMessage
            )
        )
        if (validatePassword.status)
            return

        loginRequest()
    }

    private fun loginRequest() {
        viewModelScope.launch {
            repository.login().onEach { dataState ->
                onUIEvent(LoginUIEvents.LoadingStatusChanged(dataState.loading))
                dataState.data?.let {
                    apiEvents.emit(LoginApiEvents.OnSuccess)
                }
                dataState.error?.let { error ->
                    apiEvents.emit(LoginApiEvents.PopUpErrorMessage(error))
                }
            }.launchIn(viewModelScope)
        }
    }

}