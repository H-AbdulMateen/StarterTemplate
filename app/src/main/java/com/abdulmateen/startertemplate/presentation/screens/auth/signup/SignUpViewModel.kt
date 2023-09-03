package com.abdulmateen.startertemplate.presentation.screens.auth.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.startertemplate.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _uiState = mutableStateOf(SignUpUIState())
    val uiState: State<SignUpUIState> = _uiState

    val apiEvents = MutableSharedFlow<SignUpApiEvents>()

    fun onUIEvent(uiEvents: SignUpUIEvents) {
        when (uiEvents) {
            is SignUpUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(
                    email = uiEvents.email
                )
            }

            is SignUpUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(
                    password = uiEvents.password
                )
            }

            SignUpUIEvents.OnSignUpClick -> {
                validateFields()
            }

            is SignUpUIEvents.LoadingStatusChanged -> {
                _uiState.value = _uiState.value.copy(
                    loading = uiEvents.isLoading
                )
            }

            is SignUpUIEvents.UpdatePasswordVisibilityStatus -> {
                _uiState.value = _uiState.value.copy(
                    passwordVisibility = !uiState.value.passwordVisibility
                )
            }

            is SignUpUIEvents.UpdateEmailErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasEmailError = uiEvents.hasError,
                    emailError = uiEvents.message
                )
            }

            is SignUpUIEvents.UpdatePasswordErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasPasswordError = uiEvents.hasError,
                    passwordError = uiEvents.message
                )
            }

            is SignUpUIEvents.UpdateAddress -> {
                _uiState.value = _uiState.value.copy(
                    address = uiEvents.address
                )
            }
            is SignUpUIEvents.UpdateAddressErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasAddressError = uiEvents.hasError,
                    addressError = uiEvents.message
                )
            }
            is SignUpUIEvents.UpdateConfirmPassword -> {
                _uiState.value = _uiState.value.copy(
                    confirmPassword = uiEvents.password
                )
            }
            is SignUpUIEvents.UpdateConfirmPasswordErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasConfirmPasswordError = uiEvents.hasError,
                    confirmPasswordError = uiEvents.message
                )
            }
            is SignUpUIEvents.UpdateFullName -> {
                _uiState.value = _uiState.value.copy(
                    fullName = uiEvents.name
                )
            }
            is SignUpUIEvents.UpdateFullNameErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasFullNameError = uiEvents.hasError,
                    fullNameError = uiEvents.message
                )
            }
            is SignUpUIEvents.UpdatePhoneNumber -> {
                _uiState.value = _uiState.value.copy(
                    phoneNumber = uiEvents.phoneNumber
                )
            }
            is SignUpUIEvents.UpdatePhoneNumberErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasPhoneNumberError = uiEvents.hasError,
                    phoneNumberError = uiEvents.message
                )
            }

            is SignUpUIEvents.UpdateTermsAcceptedStatus ->{
                _uiState.value = _uiState.value.copy(
                    hasTermsAccepted = !uiState.value.hasTermsAccepted
                )
            }

            is SignUpUIEvents.UpdateConfirmPasswordVisibilityStatus -> {
                _uiState.value = _uiState.value.copy(
                    confirmPasswordVisibility = !_uiState.value.confirmPasswordVisibility
                )
            }

            is SignUpUIEvents.UpdateBitmap -> {
                _uiState.value = _uiState.value.copy(
                    bitmap = uiEvents.bitmap
                )
            }

            is SignUpUIEvents.UpdateDateOfBirth -> {
                _uiState.value = _uiState.value.copy(
                    dateOfBirth = uiEvents.selectedDate
                )
            }
        }
    }

    private fun validateFields() {
        viewModelScope.launch {
            val validateFullName = Validator.validateIsFieldEmpty(uiState.value.fullName)
            onUIEvent(
                SignUpUIEvents.UpdateFullNameErrorStatus(
                    hasError = validateFullName.status,
                    message = validateFullName.errorMessage
                )
            )
            if (validateFullName.status)
                return@launch

            val validateEmail = Validator.validateEmail(uiState.value.email)
            onUIEvent(
                SignUpUIEvents.UpdateEmailErrorStatus(
                    hasError = validateEmail.status,
                    message = validateEmail.errorMessage
                )
            )
            if (validateEmail.status)
                return@launch


            val validatePhoneNumber = Validator.validateIsFieldEmpty(uiState.value.phoneNumber)
            onUIEvent(
                SignUpUIEvents.UpdatePhoneNumberErrorStatus(
                    hasError = validatePhoneNumber.status,
                    message = validatePhoneNumber.errorMessage
                )
            )
            if (validatePhoneNumber.status)
                return@launch

            val validateAddress = Validator.validateIsFieldEmpty(uiState.value.address)
            onUIEvent(
                SignUpUIEvents.UpdateAddressErrorStatus(
                    hasError = validateAddress.status,
                    message = validateAddress.errorMessage
                )
            )
            if (validateAddress.status)
                return@launch



            val validatePassword = Validator.validatePassword(uiState.value.password)
            onUIEvent(
                SignUpUIEvents.UpdatePasswordErrorStatus(
                    hasError = validatePassword.status,
                    message = validatePassword.errorMessage
                )
            )
            if (validatePassword.status)
                return@launch

            val validateConfirmPassword = Validator.validateConfirmPassword(password = uiState.value.password ,confirmPassword = uiState.value.confirmPassword)
            onUIEvent(
                SignUpUIEvents.UpdateConfirmPasswordErrorStatus(
                    hasError = validateConfirmPassword.status,
                    message = validateConfirmPassword.errorMessage
                )
            )
            if (validateConfirmPassword.status)
                return@launch

            if (uiState.value.hasTermsAccepted){
                signUpRequest()
            }else{
                apiEvents.emit(SignUpApiEvents.PopUpErrorMessage(message = "Please accept Terms & Conditions"))
            }
        }

    }

    private fun signUpRequest() {
        viewModelScope.launch {

        }
    }

}