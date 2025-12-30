package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single<ConnectivityManager> {
        val context: Context = androidContext()
        context.getSystemService(ConnectivityManager::class.java)
    }

    single<NetworkMonitor> { NetworkMonitorImpl(get()) }
}
