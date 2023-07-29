package com.abdulmateen.startertemplate.presentation.navigation

sealed class ScreenRoute(val route: String){
    object Login: ScreenRoute("login")
    object SignUp: ScreenRoute("sign_up")
    object Main: ScreenRoute("main")
    object Dashboard: ScreenRoute("dashboard")
    object Home: ScreenRoute("home")
    object Notification: ScreenRoute("notification")
    object Requests: ScreenRoute("requests")
    object Profile: ScreenRoute("profile")
    object MyBids: ScreenRoute("my_bids")

}
