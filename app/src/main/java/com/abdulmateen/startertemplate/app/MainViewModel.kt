package com.abdulmateen.startertemplate.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.startertemplate.common.domain.DataStoreManager
import com.abdulmateen.startertemplate.common.utils.PrefKeys
import com.abdulmateen.startertemplate.common.utils.PreferencesKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
): ViewModel(){
    private val _uiState = MutableStateFlow(LoadingUiState())
    private var hasLoadedInitialData: Boolean = false
    val uiState = _uiState
        .onStart {
            if (!hasLoadedInitialData) {
                observeSession()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoadingUiState()
        )

    init {
        viewModelScope.launch {
//            delay(1000L)
            observeSession()
        }
    }

    private suspend fun observeSession(){
        val authInfo = dataStoreManager.getBoolValue(PrefKeys.IS_LOGGED_IN)
        _uiState.update {
            it.copy(
                isReady = true,
                isCheckingAuth = false,
                isLoggedIn = authInfo
            )
        }
    }
}