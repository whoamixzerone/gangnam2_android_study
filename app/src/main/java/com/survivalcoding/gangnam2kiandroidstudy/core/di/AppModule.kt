package com.survivalcoding.gangnam2kiandroidstudy.core.di

val recipeAppModule = listOf(
    repositoryModule,
    dataSourceModule,
    useCaseModule,
    viewModelModule
).flatten()
