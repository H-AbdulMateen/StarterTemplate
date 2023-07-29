package com.abdulmateen.startertemplate.di

import androidx.room.Room
import com.abdulmateen.startertemplate.MyApplication
import com.abdulmateen.startertemplate.data.cache.AppDatabase
import com.abdulmateen.startertemplate.data.cache.dao.UserDao
import com.abdulmateen.startertemplate.data.cache.mapper.UserEntityMapper
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