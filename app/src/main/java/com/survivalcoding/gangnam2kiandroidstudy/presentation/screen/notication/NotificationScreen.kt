package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationScreen(
    uiState: NotificationState,
    modifier: Modifier = Modifier
) {
    Column {
        Text("Notification Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NotificationScreenPreview() {
    Scaffold { innerPadding ->
        NotificationScreen(
            uiState = NotificationState(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}