package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.Context
import android.util.Log
import java.util.concurrent.CancellationException
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.GoogleAuthProvider
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override fun signUp(
        email: String,
        password: String,
    ): Flow<Result<Unit, String>> = flow {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            emit(Result.Success(Unit))
        } catch (e: FirebaseAuthException) {
            Log.e("AuthRepositoryImpl", "$e")
            val message = when (e) {
                is FirebaseAuthWeakPasswordException -> "비밀번호가 너무 취약합니다."
                is FirebaseAuthUserCollisionException -> "이미 가입된 이메일입니다."
                is FirebaseAuthInvalidCredentialsException -> "이메일 형식이 아닙니다."
                else -> e.localizedMessage ?: "인증 오류가 발생했습니다."
            }

            emit(Result.Failure(message))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.Failure("알 수 없는 오류가 발생했습니다. error: ${e.message}"))
        }
    }

    override fun signIn(
        email: String,
        password: String,
    ): Flow<Result<Unit, String>> = flow {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()

            emit(Result.Success(Unit))
        } catch (e: FirebaseAuthException) {
            Log.e("AuthRepositoryImpl", "$e")
            val message = when (e) {
                is FirebaseAuthInvalidCredentialsException -> "이메일 혹은 비밀번호를 확인해주세요."
                is FirebaseAuthInvalidUserException -> "이메일이 일치하지 않습니다."
                else -> e.localizedMessage ?: "인증 오류가 발생했습니다."
            }

            emit(Result.Failure(message))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.Failure("알 수 없는 오류가 발생했습니다. error: ${e.message}"))
        }
    }

    override fun signInWithGoogle(): Flow<Result<Unit, String>> = flow {
        try {
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.web_client_id))
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context = context, request = request)

            val idTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            val authCredential = GoogleAuthProvider.getCredential(idTokenCredential.idToken, null)

            firebaseAuth.signInWithCredential(authCredential).await()

            emit(Result.Success(Unit))
        } catch (_: NoCredentialException) {
            Log.e("AuthRepositoryImpl", "등록된 계정이 없습니다.")
            emit(Result.Failure("기기에 등록된 Google 계정이 없습니다. 설정에서 계정을 추가해 주세요."))
        } catch (e: GetCredentialException) {
            Log.e("AuthRepositoryImpl", "Type: ${e.type}, Message: ${e.message}")
            emit(Result.Failure("로그인 프로세스 중 오류가 발생했습니다."))
        } catch (e: FirebaseAuthException) {
            Log.e("AuthRepositoryImpl", "$e")
            emit(Result.Failure(e.localizedMessage ?: "구글 로그인 오류가 발생했습니다."))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.Failure("알 수 없는 오류가 발생했습니다. error: ${e.message}"))
        }
    }

}
