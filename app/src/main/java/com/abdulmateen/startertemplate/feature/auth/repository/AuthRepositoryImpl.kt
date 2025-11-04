package com.abdulmateen.startertemplate.feature.auth.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.abdulmateen.startertemplate.common.domain.models.DataState
import com.abdulmateen.startertemplate.common.domain.models.User
import com.abdulmateen.startertemplate.core.data.local.room.dao.UserDao
import com.abdulmateen.startertemplate.core.data.remote.RetroService
import com.abdulmateen.startertemplate.common.utils.AppConstants
import com.abdulmateen.startertemplate.common.utils.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AuthRepositoryImpl(
    private val retroService: RetroService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>
): AuthRepository {
    override fun login(): Flow<DataState<Boolean>> = flow {
        try {
            emit(DataState.Companion.loading())
            saveDataInPrefs(
                user = User(
                    userId = "testId",
                    name = "testName",
                    email = "test@gmail.com",
                    mobileNumber = "",
                    address = "",
                    postalCode = "",
                    city = "",
                    imageData = ""

                )
            )
            emit(DataState.Companion.success(data = true))
        } catch (ex: Exception) {
            Log.d(AppConstants.TAG_DEBUG, ex.localizedMessage ?: AppConstants.UNKNOWN_ERROR)
        }
    }

    private suspend fun saveDataInPrefs(user: User) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = true
            preferences[PreferencesKeys.USER_ID] = user.userId
            preferences[PreferencesKeys.EMAIL] = user.email
        }
    }
}