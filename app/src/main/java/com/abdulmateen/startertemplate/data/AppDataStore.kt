package com.abdulmateen.startertemplate.data
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.startertemplate.utils.PreferencesKeys.DEVICE_TOKEN
import com.abdulmateen.startertemplate.utils.PreferencesKeys.EMAIL
import com.abdulmateen.startertemplate.utils.PreferencesKeys.IS_FIRST_TIME
import com.abdulmateen.startertemplate.utils.PreferencesKeys.IS_LOGGED_IN
import com.abdulmateen.startertemplate.utils.PreferencesKeys.USER_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val scope = CoroutineScope(Dispatchers.Main)

    val fcmToken = mutableStateOf("")
    val isLoggedIn = mutableStateOf(false)
    val userId = mutableStateOf("")
    val isFirstTime = mutableStateOf(true)
    val email = mutableStateOf("")

    fun observeData(){
        dataStore.data.onEach {preferences ->
            isLoggedIn.value = preferences[IS_LOGGED_IN] ?: false
            userId.value = preferences[USER_ID] ?: ""
            isFirstTime.value = preferences[IS_FIRST_TIME] ?: true
            fcmToken.value = preferences[DEVICE_TOKEN] ?: ""
            email.value = preferences[EMAIL] ?: ""
        }.launchIn(scope)
    }

}