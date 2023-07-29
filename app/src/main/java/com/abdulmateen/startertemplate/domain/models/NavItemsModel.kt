package com.abdulmateen.startertemplate.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemsModel(
    val route: String,
    val label: String,
    val icon: ImageVector
)
