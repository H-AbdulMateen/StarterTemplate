package com.abdulmateen.startertemplate.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.startertemplate.data.cache.dao.UserDao
import com.abdulmateen.startertemplate.data.remote.RetroService
import com.abdulmateen.startertemplate.repository.auth.AuthRepository
import com.abdulmateen.startertemplate.repository.auth.AuthRepositoryImpl
import com.abdulmateen.startertemplate.repository.main.MainRepository
import com.abdulmateen.startertemplate.repository.main.MainRepositoryImpl
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

    @ViewModelScoped
    @Provides
    fun provideMainRepository(
        retroService: RetroService,
        userDao: UserDao,
        dataStore: DataStore<Preferences>
    ): MainRepository = MainRepositoryImpl(
        retroService = retroService,
        userDao = userDao,
        dataStore = dataStore
    )
}