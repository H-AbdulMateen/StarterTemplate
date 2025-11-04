package com.abdulmateen.startertemplate.feature.auth.navigation

import kotlinx.serialization.Serializable

sealed interface AuthScreenRoutes {
    @Serializable
    data object Login : AuthScreenRoutes
    @Serializable
    data object SignUp : AuthScreenRoutes
    @Serializable
    data object ForgotPassword : AuthScreenRoutes
    @Serializable
    data object ResetPassword : AuthScreenRoutes
    @Serializable
    data object VerifyEmail : AuthScreenRoutes
    @Serializable
    data object VerifyPhone : AuthScreenRoutes
    @Serializable
    data object VerifyOTP : AuthScreenRoutes
    @Serializable
    data object VerifyOTPPhone : AuthScreenRoutes
    @Serializable
    data object VerifyOTPEmail : AuthScreenRoutes

}
