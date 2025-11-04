package com.abdulmateen.startertemplate.feature.main.dashboard.home.product_list

import android.graphics.Bitmap

data class HomeScreenUIState(
    val bitmap: Bitmap? = null,
    val requestDetail: String = "",
    val isLoading: Boolean = false,
    val bitmapHasError: Boolean = false,
    val bitmapError: String = "",
    val requestDetailHasError: Boolean = false,
    val requestDetailError: String = ""
)