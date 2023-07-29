package com.lineztech.selfee.presentation.screens.main.requests

sealed class RequestsApiEvents{
    data class PopUpErrorMessage(val message: String): RequestsApiEvents()
    object OnSuccess: RequestsApiEvents()
}
