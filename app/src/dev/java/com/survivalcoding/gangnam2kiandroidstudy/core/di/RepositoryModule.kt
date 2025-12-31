package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ClipboardRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val recipeRepositoryModule = module {

    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
}

val ingredientRepositoryModule = module {

    single<IngredientRepository> { IngredientRepositoryImpl(get()) }
}

val procedureRepositoryModule = module {

    single<ProcedureRepository> { ProcedureRepositoryImpl(get()) }
}

val clipboardRepositoryModule = module {
    single<ClipboardRepository> { ClipboardRepositoryImpl(androidContext()) }
}

val repositoryModule = listOf(
    recipeRepositoryModule,
    ingredientRepositoryModule,
    procedureRepositoryModule,
    clipboardRepositoryModule
)