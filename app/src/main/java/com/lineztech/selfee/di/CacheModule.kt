package com.lineztech.selfee.di

import androidx.room.Room
import com.lineztech.selfee.MyApplication
import com.lineztech.selfee.data.cache.AppDatabase
import com.lineztech.selfee.data.cache.dao.UserDao
import com.lineztech.selfee.data.cache.mapper.UserEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: MyApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideUserEntity(): UserEntityMapper = UserEntityMapper()


}