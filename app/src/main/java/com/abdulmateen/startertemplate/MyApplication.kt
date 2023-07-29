package com.abdulmateen.startertemplate

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.startertemplate.data.AppDataStore
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