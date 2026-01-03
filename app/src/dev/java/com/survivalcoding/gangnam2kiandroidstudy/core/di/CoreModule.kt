package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single<android.net.ConnectivityManager> {
        val context: android.content.Context = androidContext()
        context.getSystemService(android.net.ConnectivityManager::class.java)
    }

    single<NetworkMonitor> { NetworkMonitorImpl(get()) }

    single {
        Room
            .databaseBuilder(
                androidContext(),
                AppDataBase::class.java,
                "food-recipe"
            )
            .build()
    }
    
    single { get<AppDataBase>().userDao() }
}
