package com.lineztech.selfee.presentation.screens.main.dashboard.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _uiState = mutableStateOf(HomeScreenUIState())
    val uiState: State<HomeScreenUIState> = _uiState

    val apiEvents = MutableSharedFlow<HomeScreenApiEvents>()

    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.SetBitmap -> {
                _uiState.value = _uiState.value.copy(
                    bitmap = event.bitmap
                )
            }

            is HomeScreenEvents.UpdateRequestDetails -> {
                _uiState.value = _uiState.value.copy(
                    requestDetail = event.requestDetail
                )
            }
            HomeScreenEvents.UpdateLoadingStatus -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = !uiState.value.isLoading
                )
            }
            is HomeScreenEvents.UpdateBitmapErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    bitmapHasError = event.error,
                    bitmapError = event.message
                )
            }
            is HomeScreenEvents.UpdateRequestDetailErrorStatus -> {
                _uiState.value = _uiState.value.copy(
                    requestDetailHasError = event.error,
                    requestDetailError = event.message
                )
            }
            HomeScreenEvents.Submit -> {
                validateFields()
            }
        }
    }

    private fun validateFields() {

    }
}