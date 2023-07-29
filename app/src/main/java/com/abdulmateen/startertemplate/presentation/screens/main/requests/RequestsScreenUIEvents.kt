package com.abdulmateen.startertemplate.presentation.screens.main.requests

import com.abdulmateen.startertemplate.domain.models.RequestItemModel

sealed class RequestsScreenUIEvents{
    data class UpdateLoadingStatus(val loading: Boolean): RequestsScreenUIEvents()
    data class UpdateRequestsList(val list: List<RequestItemModel>): RequestsScreenUIEvents()
}
