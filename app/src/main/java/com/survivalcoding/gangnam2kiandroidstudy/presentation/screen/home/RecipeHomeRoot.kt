package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RecipeHomeRoot(
    modifier: Modifier = Modifier,
    viewModel: RecipeHomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecipeHomeScreen(
        state = state,
        modifier = modifier,
        onSelectedCategory = viewModel::updateSelectedCategory
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecipeHomeRootPreview() {
    Scaffold { innerPadding ->
        RecipeHomeRoot(modifier = Modifier.padding(innerPadding))
    }
}