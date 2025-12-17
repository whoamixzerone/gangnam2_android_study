package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val getRecipesUseCaseModule = module {

    singleOf(::GetRecipesUseCase)
}

val getRecipeDetailsUseCaseModule = module {

    single { GetRecipeDetailsUseCase(get(), get(), get()) }
}

val useCaseModule = listOf(
    getRecipesUseCaseModule,
    getRecipeDetailsUseCaseModule
)
