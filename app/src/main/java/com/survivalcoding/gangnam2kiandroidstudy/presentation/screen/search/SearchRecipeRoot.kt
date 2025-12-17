package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipeRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipeScreen(
        state = state,
        modifier = modifier,
        onClickSearch = viewModel::performSearch,
        onUpdateSearch = viewModel::updateSearch,
        toggleFilterSetting = viewModel::toggleFilterSetting,
        onUpdateFilterSearch = viewModel::updateFilterSearchState
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchRecipeRootPreview() {
    Scaffold { innerPadding ->
        SearchRecipeRoot(modifier = Modifier.padding(innerPadding))
    }
}