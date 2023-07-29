package com.lineztech.selfee.presentation.screens.auth.signup

sealed class SignUpUIEvents{
    data class LoadingStatusChanged(val isLoading: Boolean): SignUpUIEvents()
    data class UpdateFullName(val name: String) : SignUpUIEvents()
    data class UpdateFullNameErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateEmail(val email: String) : SignUpUIEvents()
    data class UpdateEmailErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdatePhoneNumber(val phoneNumber: String): SignUpUIEvents()
    data class UpdatePhoneNumberErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateAddress(val address: String): SignUpUIEvents()
    data class UpdateAddressErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdatePassword(val password: String): SignUpUIEvents()
    data class UpdatePasswordErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateConfirmPassword(val password: String): SignUpUIEvents()
    data class UpdateConfirmPasswordErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    object UpdateTermsAcceptedStatus: SignUpUIEvents()
    object OnSignUpClick: SignUpUIEvents()
    object UpdatePasswordVisibilityStatus: SignUpUIEvents()
    object UpdateConfirmPasswordVisibilityStatus: SignUpUIEvents()

}
