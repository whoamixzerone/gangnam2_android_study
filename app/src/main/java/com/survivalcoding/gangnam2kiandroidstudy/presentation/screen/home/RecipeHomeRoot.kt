package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun RecipeHomeRoot(
    modifier: Modifier = Modifier,
    viewModel: RecipeHomeViewModel = viewModel(
        factory = RecipeHomeViewModel.factory(LocalContext.current.applicationContext as AppApplication)
    )
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