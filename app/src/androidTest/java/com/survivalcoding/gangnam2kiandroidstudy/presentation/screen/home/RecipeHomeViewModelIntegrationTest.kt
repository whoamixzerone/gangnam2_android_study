package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.UserDao
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppDataBase
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.UserRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RecipeHomeViewModelIntegrationTest {

    private lateinit var db: AppDataBase
    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var viewModel: RecipeHomeViewModel

    private val fakeBookmarkRepository = object : BookmarkRepository {
        override fun updateBookmarkRecipe(id: Int): Flow<Result<Unit, String>> {
            TODO("Not yet implemented")
        }

        override fun getBookmarks(): Flow<Result<List<Int>, String>> {
            TODO("Not yet implemented")
        }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // 공식 문서 권장 방식: In-memory 데이터베이스 생성
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        
        userDao = db.userDao()
        userRepository = UserRepositoryImpl(userDao)
        recipeRepository = MockRecipeRepository()
        
        viewModel = RecipeHomeViewModel(recipeRepository, userRepository, fakeBookmarkRepository)
        
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    fun toggleBookmark_integration_test_saves_data_to_in_memory_db() = runTest {
        // Arrange
        val targetRecipeId = 101

        // Act
        viewModel.onAction(RecipeHomeAction.ToggleBookmark(targetRecipeId))

        // Assert
        // Flow를 통해 데이터가 DB에 반영될 때까지 대기 및 검증
        val savedUser = userDao.loadById(1).first { user -> 
            user != null && user.recipeIds?.contains(targetRecipeId) == true 
        }

        assertTrue(savedUser!!.recipeIds!!.contains(targetRecipeId))
        assertEquals(1, savedUser.id)
    }
}