package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.Home
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.Main
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.Notifications
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.Profile
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.RecipeDetail
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.SavedRecipes
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.SearchRecipe
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.SignIn
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.SignUp
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route.Splash
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.RecipeHomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notication.NotificationRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile.ProfileRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail.RecipeDetailRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved.SavedRecipeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signin.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashRoot

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkUri: String? = null,
) {
    val topLevelBackStack = rememberNavBackStack(Splash)
    val backStack = rememberNavBackStack(Home)

    LaunchedEffect(deepLinkUri) {
        val uri = deepLinkUri?.toUri() ?: return@LaunchedEffect
        val activeLink = DeepLink.fromUri(uri) ?: return@LaunchedEffect

        when (activeLink) {
            DeepLink.SavedRecipes -> {
                topLevelBackStack.clear()
                backStack.clear()

                topLevelBackStack.add(Main())
                backStack.add(SavedRecipes)
            }
            is DeepLink.RecipeDetail -> {
                topLevelBackStack.clear()
                backStack.clear()

                topLevelBackStack.add(Main())
                backStack.add(SavedRecipes)
                topLevelBackStack.add(RecipeDetail(activeLink.id))
            }
        }
    }

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Splash> {
                SplashRoot(onNavigateToSignIn = {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(SignIn)
                })
            }
            entry<SignIn> {
                SignInRoot(
                    navigateSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Main())
                    },
                    navigateSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(SignUp)
                    }
                )
            }
            entry<SignUp> {
                SignUpRoot(
                    navigateSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(SignIn)
                    }
                )
            }
            entry<Main> { key ->
                MainScreen(
                    backStack = backStack,
                    body = { it ->
                        NavDisplay(
                            modifier = it,
                            backStack = backStack,
                            entryProvider = entryProvider {
                                entry<Home> {
                                    RecipeHomeRoot(
                                        onNavigateToSearchRecipe = {
                                            topLevelBackStack.add(SearchRecipe)
                                        },
                                        onNavigateToDetail = { recipeId ->
                                            topLevelBackStack.removeIf { navKey ->
                                                navKey is RecipeDetail
                                            }
                                            topLevelBackStack.add(RecipeDetail(recipeId))
                                        },
                                    )
                                }
                                entry<SavedRecipes> {
                                    SavedRecipeRoot(
                                        onNavigateToDetail = { recipeId ->
                                            topLevelBackStack.removeIf { navKey ->
                                                navKey is RecipeDetail
                                            }
                                            topLevelBackStack.add(RecipeDetail(recipeId))
                                        }
                                    )
                                }
                                entry<Notifications> {
                                    NotificationRoot()
                                }
                                entry<Profile> {
                                    ProfileRoot()
                                }
                            }
                        )
                    }
                )
            }
            entry<SearchRecipe> {
                SearchRecipeRoot(
                    onNavigateToDetail = { recipeId ->
                        topLevelBackStack.removeIf { navKey ->
                            navKey is RecipeDetail
                        }
                        topLevelBackStack.add(RecipeDetail(recipeId))
                    }
                )
            }
            entry<RecipeDetail> { key ->
                RecipeDetailRoot(
                    recipeId = key.recipeId,
                    onNavigateToBack = {
                        if (topLevelBackStack.isNotEmpty()) {
                            topLevelBackStack.removeAt(topLevelBackStack.lastIndex)
                        }
                    }
                )
            }
        }
    )
}
