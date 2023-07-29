package com.lineztech.selfee

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.lineztech.selfee.data.AppDataStore
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var dataStore: AppDataStore

    @Inject
    lateinit var coreDataStore: DataStore<Preferences>

    override fun onCreate() {
        super.onCreate()
        dataStore.observeData()
    }
}