package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import app.cash.turbine.test
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.future.future
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    private val networkMonitor = MutableStateFlow(true)
    private val mockMonitor = mockk<NetworkMonitor>()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { mockMonitor.isConnected } returns networkMonitor

        viewModel = SplashViewModel(mockMonitor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `초기 진입 시 네트워크가 연결되어 있다면 버튼은 활성화되고 스낵바는 뜨지 않는다`() = runTest {
        viewModel.uiState.test {
            val item = awaitItem()

            assertTrue(item.isButtonEnabled)
        }

        viewModel.uiEvent.test {
            expectNoEvents()
        }
    }

    @Test
    fun `네트워크가 끊기면 버튼이 비활성화되고 연결 끊김 스낵바가 발생한다`() = runTest {
        networkMonitor.value = false
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isButtonEnabled)

        viewModel.uiEvent.test {
            val event = awaitItem()

            assertTrue(event is SplashEvent.ShowSnackbar)
            assertEquals("네트워크 연결을 확인해주세요.", event.message)
        }
    }

    @Test
    fun `비연결 상태에서 다시 연결되면 버튼이 활성화되고 연결 알림 스낵바가 발생한다`() = runTest {
        networkMonitor.value = false
        viewModel = SplashViewModel(mockMonitor)
        advanceUntilIdle()

        networkMonitor.value = true
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.isButtonEnabled)

        viewModel.uiEvent.test {
            val event = expectMostRecentItem()

            assertTrue(event is SplashEvent.ShowSnackbar)
            assertEquals("네트워크가 연결되었습니다.", event.message)
        }
    }
}