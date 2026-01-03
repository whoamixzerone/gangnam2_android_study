package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppDataBase
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single<ConnectivityManager> {
        val context: Context = androidContext()
        context.getSystemService(ConnectivityManager::class.java)
    }

    single<NetworkMonitor> { NetworkMonitorImpl(get()) }

    single {
        Room
            .inMemoryDatabaseBuilder(
                androidContext(),
                AppDataBase::class.java
            )
            .build()
    }

    single { get<AppDataBase>().userDao() }

    single<FirebaseAuth> {
        val auth = FirebaseAuth.getInstance()
        auth.useEmulator("10.0.2.2", 9099)
        auth
    }
}
