package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.RecipeHomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication.NotificationViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved.SavedRecipeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin.SignInViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeViewModelModule = module {

    viewModelOf(::RecipeHomeViewModel)
}

val notificationViewModelModule = module {

    viewModelOf(::NotificationViewModel)
}

val profileViewModelModule = module {

    viewModelOf(::ProfileViewModel)
}

val recipeDetailViewModelModule = module {

    viewModel { (recipeId: Int) ->
        RecipeDetailViewModel(
            getRecipeDetailsUseCase = get(),
            copyLinkUseCase = get(),
            recipeId = recipeId
        )
    }
}

val savedRecipeViewModelModule = module {

    viewModelOf(::SavedRecipeViewModel)
}

val searchRecipeViewModelModule = module {

    viewModelOf(::SearchRecipeViewModel)
}

val signInViewModelModule = module {

    viewModelOf(::SignInViewModel)
}

val signUpViewModelModule = module {

    viewModelOf(::SignUpViewModel)
}

val splashViewModelModule = module {
    viewModelOf(::SplashViewModel)
}

val viewModelModule = listOf(
    homeViewModelModule,
    notificationViewModelModule,
    profileViewModelModule,
    recipeDetailViewModelModule,
    savedRecipeViewModelModule,
    searchRecipeViewModelModule,
    signInViewModelModule,
    signUpViewModelModule,
    splashViewModelModule
)
