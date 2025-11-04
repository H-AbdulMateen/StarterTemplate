package com.abdulmateen.startertemplate.feature.auth.presentation.login

sealed class LoginApiEvents{
    data class PopUpErrorMessage(val message: String): LoginApiEvents()
    object OnSuccess: LoginApiEvents()
}
