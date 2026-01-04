package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.UserRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val recipeRepositoryModule = module {

    single<RecipeRepository> { MockRecipeRepository() }
}

val ingredientRepositoryModule = module {

    single<IngredientRepository> { MockIngredientRepository() }
}

val procedureRepositoryModule = module {

    single<ProcedureRepository> { MockProcedureRepository() }
}

val clipboardRepositoryModule = module {
    single<ClipboardRepository> { MockClipboardRepository() }
}

val userRepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}

val authRepositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(androidContext(), get()) }
}

val bookmarkRepositoryModule = module {
    single<BookmarkRepository> { BookmarkRepositoryImpl(get(), get()) }
}

val repositoryModule = listOf(
    recipeRepositoryModule,
    ingredientRepositoryModule,
    procedureRepositoryModule,
    clipboardRepositoryModule,
    userRepositoryModule,
    authRepositoryModule,
    bookmarkRepositoryModule
)
