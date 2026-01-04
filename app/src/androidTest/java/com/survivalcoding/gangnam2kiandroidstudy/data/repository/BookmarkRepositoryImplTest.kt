package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class BookmarkRepositoryImplTest : KoinTest {

    // Koin에서 staging 환경에 맞춰 설정된 인스턴스를 주입받습니다.
    private val bookmarkRepository: BookmarkRepository by inject()
    private val firebaseAuth: FirebaseAuth by inject()
    private val firestore: FirebaseFirestore by inject()

    @Before
    fun setUp() {
        // CoreModule(staging)에서 이미 에뮬레이터 설정을 마쳤으므로 
        // 여기서 별도의 useEmulator()를 호출하지 않습니다.
    }

    @After
    fun tearDown() {
        runBlocking {
            Log.d("BookmarkTest", "Starting tearDown")
            try {
                withTimeout(3000) {
                    // 테스트 완료 후 유저 삭제
                    firebaseAuth.currentUser?.delete()?.await()
                    Log.d("BookmarkTest", "Test user deleted successfully")
                }
            } catch (e: Exception) {
                Log.w("BookmarkTest", "Failed to delete test user in tearDown", e)
            }
            firebaseAuth.signOut()
            Log.d("BookmarkTest", "tearDown completed")
        }
    }

    private suspend fun createTestUser(): String {
        val email = "test_${UUID.randomUUID()}@example.com"
        val password = "password123"
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val uid = firebaseAuth.currentUser!!.uid
        // Emulator synchronization delay
        delay(500)
        return uid
    }

    @Test
    fun updateBookmarkRecipe_addsAndRemovesBookmark() {
        runBlocking {
            Log.d("BookmarkTest", "Starting updateBookmarkRecipe_addsAndRemovesBookmark")
            createTestUser()
            val recipeId = 100
            
            // Extra delay to ensure emulator and gRPC are ready
            delay(2000)
            
            // Warm up Firestore to ensure connection is ready
            waitForFirestore()

            // 1. Add bookmark
            Log.d("BookmarkTest", "Step 1: Adding bookmark")
            val addResult = bookmarkRepository.updateBookmarkRecipe(recipeId).first()
            assertTrue("Add bookmark failed: $addResult", addResult is Result.Success)

            // Verify in getBookmarks - 데이터가 반영될 때까지 최대 5초 대기
            Log.d("BookmarkTest", "Step 2: Verifying bookmark addition")
            withTimeout(5000) {
                val result = bookmarkRepository.getBookmarks()
                    .filter { it is Result.Success && it.data.contains(recipeId) }
                    .first()
                assertTrue(result is Result.Success)
            }

            // 2. Remove bookmark (toggle)
            Log.d("BookmarkTest", "Step 3: Removing bookmark")
            val removeResult = bookmarkRepository.updateBookmarkRecipe(recipeId).first()
            assertTrue("Remove bookmark failed: $removeResult", removeResult is Result.Success)

            // Verify in getBookmarks - 데이터가 제거될 때까지 최대 5초 대기
            Log.d("BookmarkTest", "Step 4: Verifying bookmark removal")
            withTimeout(5000) {
                val result = bookmarkRepository.getBookmarks()
                    .filter { it is Result.Success && !it.data.contains(recipeId) }
                    .first()
                assertTrue(result is Result.Success)
            }
            Log.d("BookmarkTest", "Test completed successfully")
        }
    }

    private suspend fun waitForFirestore() {
        val maxRetries = 10
        var currentRetry = 0
        while (currentRetry < maxRetries) {
            try {
                firestore.collection("ping").document("pong").get().await()
                Log.d("BookmarkTest", "Firestore is ready")
                return
            } catch (e: Exception) {
                currentRetry++
                Log.w("BookmarkTest", "Firestore not ready yet (attempt $currentRetry/$maxRetries)", e)
                delay(1000)
            }
        }
        throw IllegalStateException("Firestore not ready after $maxRetries attempts")
    }

    @Test
    fun getBookmarks_returnsEmptyListForNewUser() {
        runBlocking {
            createTestUser()

            val result = bookmarkRepository.getBookmarks().first()

            assertTrue(result is Result.Success)
            assertEquals(0, (result as Result.Success).data.size)
        }
    }

    @Test
    fun updateBookmarkRecipe_failsWhenNotLoggedIn() {
        runBlocking {
            firebaseAuth.signOut()

            val result = bookmarkRepository.updateBookmarkRecipe(1).first()

            assertTrue(result is Result.Failure)
            assertEquals("User not logged in", (result as Result.Failure).error)
        }
    }
}
