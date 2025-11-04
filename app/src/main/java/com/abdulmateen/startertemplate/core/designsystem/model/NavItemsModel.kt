package com.abdulmateen.startertemplate.core.designsystem.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemsModel(
    val route: String,
    val label: String,
    val icon: ImageVector
)