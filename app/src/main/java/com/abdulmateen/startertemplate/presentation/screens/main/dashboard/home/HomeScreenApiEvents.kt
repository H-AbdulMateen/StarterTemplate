package com.abdulmateen.startertemplate.presentation.screens.main.dashboard.home

sealed class HomeScreenApiEvents{
    data class PopUpErrorMessage(val message: String): HomeScreenApiEvents()
    object Success: HomeScreenApiEvents()
}
