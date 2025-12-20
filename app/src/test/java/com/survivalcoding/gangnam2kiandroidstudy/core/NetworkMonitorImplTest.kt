package com.survivalcoding.gangnam2kiandroidstudy.core

import android.net.ConnectivityManager
import app.cash.turbine.test
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkMonitorImplTest {

    private val connectivityManager = mockk<ConnectivityManager>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `네트워크 상태가 5초 간격으로 true에서 false로 다시 true로 변하는지 검증`() = runTest {
        val networkMonitor = NetworkMonitorImpl(connectivityManager)

        networkMonitor.isConnected.test {
            assertEquals(true, awaitItem())

            testDispatcher.scheduler.advanceTimeBy(5001)
            assertEquals(false, awaitItem())

            testDispatcher.scheduler.advanceTimeBy(5000)
            assertEquals(true, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}