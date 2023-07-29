package com.abdulmateen.startertemplate.presentation.screens.main.profile

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
class ProfileViewModel @Inject constructor() : ViewModel() {
    private val _uiState = mutableStateOf(ProfileUIState())
    val uiState: State<ProfileUIState> = _uiState

    val apiEvents = MutableSharedFlow<ProfileApiEvents>()

    fun onUIEvent(event: ProfileUIEvents) {
        when (event) {
            is ProfileUIEvents.UpdateUser -> {
                _uiState.value = _uiState.value.copy(
                    fullName = event.user.name,
                    email = event.user.email,
                    phoneNumber = event.user.mobileNumber,
                    address = event.user.address,
                    postalCode = event.user.postalCode,
                    city = event.user.city
                )
            }

            is ProfileUIEvents.UpdateEmail -> {
                _uiState.value = _uiState.value.copy(
                    email = event.email
                )
            }

            is ProfileUIEvents.UpdatePassword -> {
                _uiState.value = _uiState.value.copy(
                    password = event.password
                )
            }

            ProfileUIEvents.OnSignUpClick -> {
                validateFields()
            }

            is ProfileUIEvents.LoadingStatusChanged -> {
                _uiState.value = _uiState.value.copy(
                    loading = event.isLoading
                )
            }

            is ProfileUIEvents.UpdatePasswordVisibilityStatus -> {
                _uiState.value = _uiState.value.copy(
                    passwordVisibility = !uiState.value.passwordVisibility
                )
            }

            is ProfileUIEvents.UpdateEmailErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasEmailError = event.hasError,
                    emailError = event.message
                )
            }

            is ProfileUIEvents.UpdatePasswordErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasPasswordError = event.hasError,
                    passwordError = event.message
                )
            }

            is ProfileUIEvents.UpdateAddress -> {
                _uiState.value = _uiState.value.copy(
                    address = event.address
                )
            }
            is ProfileUIEvents.UpdateAddressErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasAddressError = event.hasError,
                    addressError = event.message
                )
            }
            is ProfileUIEvents.UpdateConfirmPassword -> {
                _uiState.value = _uiState.value.copy(
                    confirmPassword = event.password
                )
            }
            is ProfileUIEvents.UpdateConfirmPasswordErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasConfirmPasswordError = event.hasError,
                    confirmPasswordError = event.message
                )
            }
            is ProfileUIEvents.UpdateFullName -> {
                _uiState.value = _uiState.value.copy(
                    fullName = event.name
                )
            }
            is ProfileUIEvents.UpdateFullNameErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasFullNameError = event.hasError,
                    fullNameError = event.message
                )
            }
            is ProfileUIEvents.UpdatePhoneNumber -> {
                _uiState.value = _uiState.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            is ProfileUIEvents.UpdatePhoneNumberErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    hasPhoneNumberError = event.hasError,
                    phoneNumberError = event.message
                )
            }

            is ProfileUIEvents.UpdateTermsAcceptedStatus ->{
                _uiState.value = _uiState.value.copy(
                    hasTermsAccepted = !uiState.value.hasTermsAccepted
                )
            }

            is ProfileUIEvents.UpdateConfirmPasswordVisibilityStatus -> {
                _uiState.value = _uiState.value.copy(
                    confirmPasswordVisibility = !_uiState.value.confirmPasswordVisibility
                )
            }

            is ProfileUIEvents.UpdateBitmap -> {
                _uiState.value = _uiState.value.copy(
                    bitmap = event.bitmap
                )
            }

            is ProfileUIEvents.UpdateBitmapError -> {
                _uiState.value = _uiState.value.copy(
                    bitmapHasError = event.error,
                    bitmapError = event.message
                )
            }
            is ProfileUIEvents.UpdateImageData -> {
                _uiState.value = _uiState.value.copy(
                    imageUrl = event.imageData
                )
            }

            is ProfileUIEvents.UpdateCity -> {
                _uiState.value = _uiState.value.copy(
                    city = event.city
                )
            }
            is ProfileUIEvents.UpdateCityError -> {
                _uiState.value = _uiState.value.copy(
                    cityHasError = event.hasError,
                    cityError = event.message
                )
            }
            is ProfileUIEvents.UpdatePostalCode -> {
                _uiState.value = _uiState.value.copy(
                    postalCode = event.postalCode
                )
            }
            is ProfileUIEvents.UpdatePostalCodeError -> {
                _uiState.value = _uiState.value.copy(
                    postalCodeHasError = event.hasError,
                    passwordError = event.message
                )
            }
        }
    }

    private fun validateFields() {
        viewModelScope.launch {
            val validateFullName = Validator.validateIsFieldEmpty(uiState.value.fullName)
            onUIEvent(
                ProfileUIEvents.UpdateFullNameErrorStatus(
                    hasError = validateFullName.status,
                    message = validateFullName.errorMessage
                )
            )
            if (validateFullName.status)
                return@launch

            val validateEmail = Validator.validateEmail(uiState.value.email)
            onUIEvent(
                ProfileUIEvents.UpdateEmailErrorStatus(
                    hasError = validateEmail.status,
                    message = validateEmail.errorMessage
                )
            )
            if (validateEmail.status)
                return@launch


            val validatePhoneNumber = Validator.validateIsFieldEmpty(uiState.value.phoneNumber)
            onUIEvent(
                ProfileUIEvents.UpdatePhoneNumberErrorStatus(
                    hasError = validatePhoneNumber.status,
                    message = validatePhoneNumber.errorMessage
                )
            )
            if (validatePhoneNumber.status)
                return@launch

            val validateAddress = Validator.validateIsFieldEmpty(uiState.value.address)
            onUIEvent(
                ProfileUIEvents.UpdateAddressErrorStatus(
                    hasError = validateAddress.status,
                    message = validateAddress.errorMessage
                )
            )
            if (validateAddress.status)
                return@launch



            val validatePassword = Validator.validatePassword(uiState.value.password)
            onUIEvent(
                ProfileUIEvents.UpdatePasswordErrorStatus(
                    hasError = validatePassword.status,
                    message = validatePassword.errorMessage
                )
            )
            if (validatePassword.status)
                return@launch

            val validateConfirmPassword = Validator.validateConfirmPassword(password = uiState.value.password ,confirmPassword = uiState.value.confirmPassword)
            onUIEvent(
                ProfileUIEvents.UpdateConfirmPasswordErrorStatus(
                    hasError = validateConfirmPassword.status,
                    message = validateConfirmPassword.errorMessage
                )
            )
            if (validateConfirmPassword.status)
                return@launch

            if (uiState.value.hasTermsAccepted){
                signUpRequest()
            }else{
                apiEvents.emit(ProfileApiEvents.PopUpErrorMessage(message = "Please accept Terms & Conditions"))
            }
        }

    }

    private fun signUpRequest() {
        viewModelScope.launch {

        }
    }

}