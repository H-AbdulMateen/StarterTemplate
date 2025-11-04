package com.abdulmateen.startertemplate.app.navigation

import kotlinx.serialization.Serializable

sealed interface AppScreenRoutes {
    @Serializable
    data object AuthGraph: AppScreenRoutes

    @Serializable
    data object Main: AppScreenRoutes

}
