package com.abdulmateen.startertemplate.feature.main.dashboard.home.product_list

import android.graphics.Bitmap

sealed class HomeScreenEvents{
    data class SetBitmap(val bitmap: Bitmap): HomeScreenEvents()
    object UpdateLoadingStatus: HomeScreenEvents()
    data class UpdateRequestDetails(val requestDetail: String): HomeScreenEvents()
    data class UpdateBitmapErrorStatus(val error: Boolean, val message: String): HomeScreenEvents()
    data class UpdateRequestDetailErrorStatus(val error: Boolean, val message: String): HomeScreenEvents()
    object Submit: HomeScreenEvents()
}