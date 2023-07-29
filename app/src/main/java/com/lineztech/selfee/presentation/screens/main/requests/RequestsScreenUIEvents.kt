package com.lineztech.selfee.presentation.screens.main.requests

import com.lineztech.selfee.domain.models.RequestItemModel

sealed class RequestsScreenUIEvents{
    data class UpdateLoadingStatus(val loading: Boolean): RequestsScreenUIEvents()
    data class UpdateRequestsList(val list: List<RequestItemModel>): RequestsScreenUIEvents()
}
