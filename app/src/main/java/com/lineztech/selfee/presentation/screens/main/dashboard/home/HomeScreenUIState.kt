package com.lineztech.selfee.presentation.screens.main.dashboard.home

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
