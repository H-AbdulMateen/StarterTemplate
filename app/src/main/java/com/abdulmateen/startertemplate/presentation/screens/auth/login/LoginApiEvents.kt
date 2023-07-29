package com.abdulmateen.startertemplate.presentation.screens.auth.login

sealed class LoginApiEvents{
    data class PopUpErrorMessage(val message: String): LoginApiEvents()
    object OnSuccess: LoginApiEvents()
}
