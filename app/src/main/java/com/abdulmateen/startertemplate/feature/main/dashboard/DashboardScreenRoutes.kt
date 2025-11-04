package com.abdulmateen.startertemplate.feature.main.dashboard

import kotlinx.serialization.Serializable

sealed interface DashboardScreenRoutes {
    @Serializable
    data object Home: DashboardScreenRoutes
    @Serializable
    data object Profile: DashboardScreenRoutes
    @Serializable
    data object Settings: DashboardScreenRoutes
}