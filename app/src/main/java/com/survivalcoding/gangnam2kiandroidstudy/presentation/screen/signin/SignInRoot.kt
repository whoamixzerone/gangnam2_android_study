package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin

import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInRoot(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    viewModel: SignInViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val showNoAccountDialog = remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                SignInEvent.NavigateToSignUp -> onNavigateToSignUp()
                SignInEvent.SignInSuccess -> onNavigateToMain()
                is SignInEvent.SignInFailure -> {
                    if (event.isNoCredential) {
                        showNoAccountDialog.value = true
                    } else {
                        Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    if (showNoAccountDialog.value) {
        AlertDialog(
            onDismissRequest = { showNoAccountDialog.value = false },
            title = { Text("Google 계정 필요") },
            text = { Text("기기에 등록된 Google 계정이 없습니다. 계정을 추가한 후 다시 시도해 주세요.") },
            confirmButton = {
                TextButton(onClick = {
                    showNoAccountDialog.value = false
                    val intent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                        putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
                    }
                    context.startActivity(intent)
                }) {
                    Text("계정 추가")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNoAccountDialog.value = false }) {
                    Text("취소")
                }
            }
        )
    }

    SignInScreen(
        state = state.value,
        modifier = modifier,
        onAction = viewModel::handleAction
    )
}