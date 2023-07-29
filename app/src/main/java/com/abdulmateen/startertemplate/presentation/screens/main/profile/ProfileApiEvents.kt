package com.abdulmateen.startertemplate.presentation.screens.main.profile

sealed class ProfileApiEvents{
    data class PopUpErrorMessage(val message: String): ProfileApiEvents()
    object OnSuccess: ProfileApiEvents()
}
