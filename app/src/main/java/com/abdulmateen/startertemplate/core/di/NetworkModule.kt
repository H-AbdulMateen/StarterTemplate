package com.abdulmateen.startertemplate.core.di

import com.abdulmateen.startertemplate.core.data.remote.RetroService
import com.abdulmateen.startertemplate.common.utils.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetroService(): RetroService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(AppConstants.BASE_URL)
            .build()
            .create(RetroService::class.java)
    }
}