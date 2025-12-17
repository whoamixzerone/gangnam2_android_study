package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import jakarta.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dataSource: RecipeDataSource
) : BookmarkRepository {

    override suspend fun unBookmarkRecipe(id: Int): Boolean {
        TODO("Bookmark 해제 기능을 추가")
        TODO("외부에서 json 데이터를 가져오기 때문에 나중에 기능 구현")
    }
}