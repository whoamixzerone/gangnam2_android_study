package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

sealed interface RecipeDetailAction {
    data object OnArrowBackClick : RecipeDetailAction
    data object OnShareClick : RecipeDetailAction
    data object OnLinkDialogCloseClick : RecipeDetailAction

    data class OnFollowClick(val userId: Int) : RecipeDetailAction
    data class UpdateTabInfo(val index: Int) : RecipeDetailAction
    data class OnDropdownMenuClick(val expanded: Boolean) : RecipeDetailAction
    data class OnCopyLinkClick(val link: String) : RecipeDetailAction

}
