package com.abdulmateen.startertemplate.presentation.screens.main.requests

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(): ViewModel() {
    private val _uiState = mutableStateOf(RequestsUIState())
    val uiState: State<RequestsUIState> = _uiState
    val apiEvents = MutableSharedFlow<RequestsApiEvents>()

    fun onUIEvent(event: RequestsScreenUIEvents){
        when(event){
            is RequestsScreenUIEvents.UpdateLoadingStatus -> {
                _uiState.value = _uiState.value.copy(
                    loading = event.loading
                )
            }
            is RequestsScreenUIEvents.UpdateRequestsList -> {
                _uiState.value = _uiState.value.copy(
                    list = event.list
                )
            }
        }
    }

}