package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRecipeDataSource(
        recipeDataSourceImpl: RecipeDataSourceImpl
    ): RecipeDataSource

    @Binds
    @Singleton
    abstract fun bindAssetDataSource(
        assetDataSourceImpl: AssetDataSourceImpl
    ): AssetDataSource

}
