package com.lineztech.selfee.repository.main

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.lineztech.selfee.data.cache.dao.UserDao
import com.lineztech.selfee.data.cache.mapper.UserEntityMapper
import com.lineztech.selfee.data.remote.RetroService
import com.lineztech.selfee.domain.DataState
import com.lineztech.selfee.domain.models.User
import com.lineztech.selfee.utils.AppConstants.TAG_DEBUG
import com.lineztech.selfee.utils.AppConstants.UNKNOWN_ERROR
import com.lineztech.selfee.utils.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepositoryImpl(
    private val retroService: RetroService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>,
    private val userEntityMapper: UserEntityMapper
): MainRepository {
    override fun login(): Flow<DataState<Boolean>> = flow {
        try {
            emit(DataState.loading())
            saveDataInPrefs(user = User(
                userId = "testId",
                name = "testName",
                email = "test@gmail.com",
                mobileNumber = "",
                address = "",
                postalCode = "",
                city = "",
                imageData = ""

            ))
            emit(DataState.success(data = true))
        }catch (ex: Exception){
            Log.d(TAG_DEBUG, ex.localizedMessage ?: UNKNOWN_ERROR)
        }
    }

    private suspend fun saveDataInPrefs(user: User) {
        dataStore.edit {preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = true
            preferences[PreferencesKeys.USER_ID] = user.userId
            preferences[PreferencesKeys.EMAIL] = user.email
        }
    }
}