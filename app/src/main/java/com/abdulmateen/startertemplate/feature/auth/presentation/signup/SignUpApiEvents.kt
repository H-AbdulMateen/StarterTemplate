package com.abdulmateen.startertemplate.feature.auth.presentation.signup

sealed class SignUpApiEvents{
    data class PopUpErrorMessage(val message: String): SignUpApiEvents()
    object OnSuccess: SignUpApiEvents()
}
