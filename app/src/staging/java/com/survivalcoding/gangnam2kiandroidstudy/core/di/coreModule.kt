package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.firestoreSettings
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
        try {
            Log.d("CoreModule", "Setting up FirebaseAuth emulator at 10.0.2.2:9099")
            auth.useEmulator("10.0.2.2", 9099)
        } catch (e: Exception) {
            Log.w("CoreModule", "FirebaseAuth emulator already set or failed: ${e.message}")
        }
        auth
    }

    single<FirebaseFirestore> {
        val fireStore = FirebaseFirestore.getInstance()
        try {
            Log.d("CoreModule", "Setting up FirebaseFirestore emulator at 10.0.2.2:8080")
            fireStore.useEmulator("10.0.2.2", 8080)
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build()
            fireStore.firestoreSettings = settings
            Log.d("CoreModule", "FirebaseFirestore settings applied: persistence disabled")
        } catch (e: Exception) {
            Log.w("CoreModule", "FirebaseFirestore emulator already set or failed: ${e.message}")
        }
        fireStore
    }
}
