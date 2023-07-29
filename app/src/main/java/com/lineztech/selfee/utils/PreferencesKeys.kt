package com.lineztech.selfee.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_ID = stringPreferencesKey("user_id")
        val IS_FIRST_TIME = booleanPreferencesKey("is_first_time_run")
        val DEVICE_TOKEN = stringPreferencesKey("device_token")
        val EMAIL = stringPreferencesKey("email")

}