package com.abdulmateen.startertemplate.presentation.screens.auth.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val passwordVisibility: Boolean = false,
    val hasEmailError: Boolean = false,
    val emailError: String = "",
    val hasPasswordError: Boolean = false,
    val passwordError: String = ""
)

