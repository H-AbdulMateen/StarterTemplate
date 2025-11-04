package com.abdulmateen.startertemplate.feature.main.dashboard.profile

sealed class ProfileApiEvents{
    data class PopUpErrorMessage(val message: String): ProfileApiEvents()
    object OnSuccess: ProfileApiEvents()
}
