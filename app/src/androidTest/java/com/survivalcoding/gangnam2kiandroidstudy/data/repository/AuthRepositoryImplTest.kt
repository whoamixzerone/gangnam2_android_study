package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class AuthRepositoryImplTest {

    private lateinit var authRepository: AuthRepositoryImpl
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        
        // Initialize FirebaseAuth and connect to emulator
        firebaseAuth = FirebaseAuth.getInstance()
        try {
            firebaseAuth.useEmulator("10.0.2.2", 9099)
        } catch (e: IllegalStateException) {
            // Emulator might already be set
        }

        authRepository = AuthRepositoryImpl(context, firebaseAuth)
    }

    @After
    fun tearDown() {
        runBlocking {
            // Delete the user first if signed in
            try {
                firebaseAuth.currentUser?.delete()?.await()
            } catch (e: Exception) {
                // Ignore if delete fails
            }
            firebaseAuth.signOut()
        }
    }

    private fun generateUniqueEmail(): String {
        return "test_${UUID.randomUUID()}@example.com"
    }

    @Test
    fun signUp_withValidEmailAndPassword_returnsSuccess() {
        runBlocking {
            val email = generateUniqueEmail()
            val password = "password1234"
            Log.d("AuthTest", "Starting signUp with $email")

            val result = authRepository.signUp(email, password).first()

            Log.d("AuthTest", "SignUp result: $result")
            assertTrue("SignUp failed: $result", result is Result.Success)
            val currentUser = firebaseAuth.currentUser
            assertEquals(email, currentUser?.email)
        }
    }

    @Test
    fun signIn_withValidEmailAndPassword_returnsSuccess() {
        runBlocking {
            val email = generateUniqueEmail()
            val password = "password1234"
            Log.d("AuthTest", "Starting signIn with $email")

            // Create user first
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            firebaseAuth.signOut()

            // Try sign in
            val result = authRepository.signIn(email, password).first()

            Log.d("AuthTest", "SignIn result: $result")
            assertTrue("SignIn failed: $result", result is Result.Success)
            val currentUser = firebaseAuth.currentUser
            assertEquals(email, currentUser?.email)
        }
    }

    @Test
    fun signUp_withInvalidEmail_returnsFailure() {
        runBlocking {
            val email = "invalid-email"
            val password = "password1234"

            val result = authRepository.signUp(email, password).first()

            assertTrue(result is Result.Failure)
            // You might want to assert specific error messages if consistent
        }
    }
}
