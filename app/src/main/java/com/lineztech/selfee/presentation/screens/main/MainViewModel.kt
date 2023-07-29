package com.lineztech.selfee.presentation.screens.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lineztech.selfee.utils.PreferencesKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel(){
    fun logout(){
        viewModelScope.launch {
            dataStore.edit {preferences ->
                preferences[PreferencesKeys.IS_LOGGED_IN] = false
            }
        }
    }
}