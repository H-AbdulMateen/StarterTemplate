package com.lineztech.selfee.di

import android.content.Context
import com.lineztech.selfee.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }


}