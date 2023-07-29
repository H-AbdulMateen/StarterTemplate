package com.lineztech.selfee.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.lineztech.selfee.data.cache.dao.UserDao
import com.lineztech.selfee.data.cache.mapper.UserEntityMapper
import com.lineztech.selfee.data.remote.RetroService
import com.lineztech.selfee.repository.auth.AuthRepository
import com.lineztech.selfee.repository.auth.AuthRepositoryImpl
import com.lineztech.selfee.repository.main.MainRepository
import com.lineztech.selfee.repository.main.MainRepositoryImpl
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
        userEntityMapper: UserEntityMapper,
        dataStore: DataStore<Preferences>
    ): AuthRepository = AuthRepositoryImpl(
        retroService = retroService,
        userDao = userDao,
        userEntityMapper = userEntityMapper,
        dataStore = dataStore
    )

    @ViewModelScoped
    @Provides
    fun provideMainRepository(
        retroService: RetroService,
        userDao: UserDao,
        userEntityMapper: UserEntityMapper,
        dataStore: DataStore<Preferences>
    ): MainRepository = MainRepositoryImpl(
        retroService = retroService,
        userDao = userDao,
        userEntityMapper = userEntityMapper,
        dataStore = dataStore
    )
}