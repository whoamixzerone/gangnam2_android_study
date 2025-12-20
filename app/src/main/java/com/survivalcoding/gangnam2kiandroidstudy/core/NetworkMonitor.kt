package com.survivalcoding.gangnam2kiandroidstudy.core

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {

    val isConnected: Flow<Boolean>
}
