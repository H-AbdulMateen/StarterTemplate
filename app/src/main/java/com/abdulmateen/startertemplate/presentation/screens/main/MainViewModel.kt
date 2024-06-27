package com.abdulmateen.startertemplate.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.startertemplate.presentation.navigation.ScreenRoute
import com.abdulmateen.startertemplate.utils.PreferencesKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel(){

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _startDestination: MutableState<String> = mutableStateOf(ScreenRoute.Login.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStore.data.collect { preferences ->
                if (preferences[PreferencesKeys.IS_LOGGED_IN] == true) {
                    _startDestination.value = ScreenRoute.Dashboard.route
                    Log.d("AppDebug", "Route State: ${_startDestination.value}")
                } else {
                    _startDestination.value = ScreenRoute.Login.route
                    Log.d("AppDebug", "Route State: ${_startDestination.value}")
                }
                _isLoading.value = false

            }

        }
    }

    fun logout(){
        viewModelScope.launch {
            dataStore.edit {preferences ->
                preferences[PreferencesKeys.IS_LOGGED_IN] = false
            }
        }
    }
}