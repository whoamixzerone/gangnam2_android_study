package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin.SignInAction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpRoot(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit = {},
    viewModel: SignUpViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event -> 
            when (event) {
                SignUpEvent.NavigateToSignIn -> onNavigateToSignIn()
                SignUpEvent.SignUpSuccess -> {
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    onNavigateToSignIn()
                }

                is SignUpEvent.SignUpFailure -> Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    SignUpScreen(
        state = state.value,
        modifier = modifier,
        onAction = viewModel::handleAction
    )
}