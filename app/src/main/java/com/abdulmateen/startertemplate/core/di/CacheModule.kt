package com.abdulmateen.startertemplate.core.di

import androidx.room.Room
import com.abdulmateen.startertemplate.app.MyApplication
import com.abdulmateen.startertemplate.core.data.local.room.AppDatabase
import com.abdulmateen.startertemplate.core.data.local.room.dao.UserDao
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
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.Companion.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }



}