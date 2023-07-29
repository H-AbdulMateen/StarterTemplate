package com.abdulmateen.startertemplate.presentation.screens.main.requests

import com.abdulmateen.startertemplate.domain.models.RequestItemModel

data class RequestsUIState(
    val loading: Boolean = false,
    val list: List<RequestItemModel> = arrayListOf()
)

