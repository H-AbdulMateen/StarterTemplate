package com.abdulmateen.startertemplate.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.startertemplate.core.data.local.room.dao.UserDao
import com.abdulmateen.startertemplate.core.data.remote.RetroService
import com.abdulmateen.startertemplate.feature.auth.repository.AuthRepository
import com.abdulmateen.startertemplate.feature.auth.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        retroService: RetroService,
        userDao: UserDao,
        dataStore: DataStore<Preferences>
    ): AuthRepository = AuthRepositoryImpl(
        retroService = retroService,
        userDao = userDao,
        dataStore = dataStore
    )

}