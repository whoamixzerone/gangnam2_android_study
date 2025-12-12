package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    uiState: ProfileState,
    modifier: Modifier = Modifier
) {
    Column {
        Text("ProfileScreen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    Scaffold { innerPadding ->
        ProfileScreen(
            uiState = ProfileState(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}