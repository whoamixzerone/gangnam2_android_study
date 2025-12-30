package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkMonitor
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val networkMonitor: NetworkMonitor,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SplashEvent>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        observeNetwork()
    }

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnStartCookingClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(SplashEvent.OnNavigateToSignIn)
                }
            }
        }
    }

    private fun observeNetwork() {
        val networkFlow = networkMonitor.isConnected.shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            replay = 1
        )

        networkFlow
            .onEach { isConnected ->
                _uiState.update {
                    it.copy(isButtonEnabled = isConnected)
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            networkFlow.collectIndexed { index, isConnected ->
                val isFirstEmit = index == 0

                when {
                    isFirstEmit && !isConnected -> {
                        _uiEvent.emit(SplashEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
                    }
                    !isFirstEmit -> {
                        val message = if (isConnected) "네트워크가 연결되었습니다." else "네트워크 연결을 확인해주세요."
                        _uiEvent.emit(SplashEvent.ShowSnackbar(message))
                    }
                }
            }
        }
    }
}
