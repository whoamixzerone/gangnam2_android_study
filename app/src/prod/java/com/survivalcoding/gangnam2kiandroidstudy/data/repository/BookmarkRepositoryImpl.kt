package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class BookmarkRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : BookmarkRepository {
    override fun updateBookmarkRecipe(id: Int): Flow<Result<Unit, String>> = flow {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            Log.e("BookmarkRepositoryImpl", "Update failed: User not logged in")
            emit(Result.Failure("User not logged in"))
            return@flow
        }

        try {
            val userRef = fireStore.collection("users").document(uid)
            
            fireStore.runTransaction { transaction ->
                val snapshot = transaction.get(userRef)
                val bookmarks = if (snapshot.exists()) {
                    (snapshot.get("bookmarks") as? List<*>)?.mapNotNull { (it as? Long)?.toInt() }?.toMutableList() ?: mutableListOf()
                } else {
                    mutableListOf()
                }

                if (bookmarks.contains(id)) {
                    bookmarks.remove(id as Any)
                } else {
                    bookmarks.add(id)
                }

                if (snapshot.exists()) {
                    transaction.update(userRef, "bookmarks", bookmarks)
                } else {
                    val newUser = hashMapOf(
                        "bookmarks" to bookmarks
                    )
                    transaction.set(userRef, newUser)
                }
            }.await()
            
            Log.d("BookmarkRepositoryImpl", "Update success for user $uid, recipe $id")
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            Log.e("BookmarkRepositoryImpl", "Update failed for user $uid, recipe $id", e)
            emit(Result.Failure(e.message ?: "Unknown error"))
        }
    }

    override fun getBookmarks(): Flow<Result<List<Int>, String>> = callbackFlow {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            trySend(Result.Failure("User not logged in"))
            close()
            return@callbackFlow
        }

        val subscription = fireStore.collection("users").document(uid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Failure(error.message ?: "Firestore error"))
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val bookmarks = (snapshot.get("bookmarks") as? List<*>)
                        ?.mapNotNull { (it as? Long)?.toInt() } ?: emptyList()
                    trySend(Result.Success(bookmarks))
                } else {
                    trySend(Result.Success(emptyList()))
                }
            }

        awaitClose { subscription.remove() }
    }
}
