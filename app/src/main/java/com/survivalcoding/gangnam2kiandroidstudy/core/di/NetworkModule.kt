package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.HttpClientFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClientFactory.createHttpClient()
}