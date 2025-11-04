package com.abdulmateen.startertemplate.feature.auth.presentation.login

sealed class LoginUIEvents{
    data class LoadingStatusChanged(val isLoading: Boolean): LoginUIEvents()
    data class UpdateEmail(val email: String) : LoginUIEvents()
    data class UpdateEmailErrorStatus(val hasError: Boolean, val message: String): LoginUIEvents()
    data class UpdatePassword(val password: String): LoginUIEvents()
    data class UpdatePasswordErrorStatus(val hasError: Boolean, val message: String): LoginUIEvents()
    object OnLoginClick: LoginUIEvents()
    object UpdatePasswordVisibilityStatus: LoginUIEvents()
}
