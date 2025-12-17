package com.survivalcoding.gangnam2kiandroidstudy.core.di

import org.koin.dsl.module

val recipeAppModule = module {

}

val recipeApp = listOf(
    repositoryModule,
    dataSourceModule,
    useCaseModule,
    viewModelModule
).flatten()
