package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val assetDataSourceModule = module {

    single<AssetDataSource> { AssetDataSourceImpl(androidContext()) }
}

val recipeDataSourceModule = module {

    single<RecipeDataSource> { RecipeDataSourceImpl() }
}

val dataSourceModule = listOf(
    assetDataSourceModule,
    recipeDataSourceModule
)
