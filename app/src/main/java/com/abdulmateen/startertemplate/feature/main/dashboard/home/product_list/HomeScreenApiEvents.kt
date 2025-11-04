package com.abdulmateen.startertemplate.feature.main.dashboard.home.product_list

sealed class HomeScreenApiEvents{
    data class PopUpErrorMessage(val message: String): HomeScreenApiEvents()
    object Success: HomeScreenApiEvents()
}