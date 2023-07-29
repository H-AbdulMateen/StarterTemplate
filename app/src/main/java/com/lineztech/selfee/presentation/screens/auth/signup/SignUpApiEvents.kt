package com.lineztech.selfee.presentation.screens.auth.signup

sealed class SignUpApiEvents{
    data class PopUpErrorMessage(val message: String): SignUpApiEvents()
    object OnSuccess: SignUpApiEvents()
}
