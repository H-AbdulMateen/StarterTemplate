package com.lineztech.selfee.presentation.screens.main.requests

import android.graphics.Bitmap
import com.lineztech.selfee.domain.models.RequestItemModel
import com.lineztech.selfee.domain.models.User

data class RequestsUIState(
    val loading: Boolean = false,
    val list: List<RequestItemModel> = arrayListOf()
)

