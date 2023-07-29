package com.abdulmateen.startertemplate.presentation.screens.auth.signup

sealed class SignUpApiEvents{
    data class PopUpErrorMessage(val message: String): SignUpApiEvents()
    object OnSuccess: SignUpApiEvents()
}
