package com.survivalcoding.gangnam2kiandroidstudy.core

import android.net.ConnectivityManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworkMonitorImpl(
    private val connectivityManager: ConnectivityManager,
) : NetworkMonitor {
    private val _status = MutableStateFlow(true)

    override val isConnected = _status.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            _status.value = false

            delay(5000)
            _status.value = true
        }
    }

}
