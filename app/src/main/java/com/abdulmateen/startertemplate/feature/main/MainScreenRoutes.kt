package com.abdulmateen.startertemplate.feature.main

import kotlinx.serialization.Serializable

sealed interface MainScreenRoutes {
    @Serializable
    data object Dashboard : MainScreenRoutes

    @Serializable
    data class ProductDetail(val productId: Int) : MainScreenRoutes
}
