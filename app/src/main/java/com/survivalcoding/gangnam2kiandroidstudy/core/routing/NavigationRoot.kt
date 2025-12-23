package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
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
fun NavigationRoot(modifier: Modifier = Modifier) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashRoot(onNavigateToSignIn = {
                    topLevelBackStack.clear()
                    topLevelBackStack.add(Route.SignIn)
                })
            }
            entry<Route.SignIn> {
                SignInRoot(
                    navigateSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    navigateSignUp = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    }
                )
            }
            entry<Route.SignUp> {
                SignUpRoot(
                    navigateSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.Main> {
                val backStack = rememberNavBackStack(Route.Home)

                MainScreen(
                    backStack = backStack,
                    body = { it ->
                        NavDisplay(
                            modifier = it,
                            backStack = backStack,
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    RecipeHomeRoot(
                                        onNavigateToSearchRecipe = {
                                            topLevelBackStack.add(Route.SearchRecipe)
                                        },
                                        onNavigateToDetail = { recipeId ->
                                            topLevelBackStack.removeIf { navKey ->
                                                navKey is Route.RecipeDetail
                                            }
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        },
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipeRoot(
                                        onNavigateToDetail = { recipeId ->
                                            topLevelBackStack.removeIf { navKey ->
                                                navKey is Route.RecipeDetail
                                            }
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        }
                                    )
                                }
                                entry<Route.Notifications> {
                                    NotificationRoot()
                                }
                                entry<Route.Profile> {
                                    ProfileRoot()
                                }
                            }
                        )
                    }
                )
            }
            entry<Route.SearchRecipe> {
                SearchRecipeRoot(
                    onNavigateToDetail = { recipeId ->
                        topLevelBackStack.removeIf { navKey ->
                            navKey is Route.RecipeDetail
                        }
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                )
            }
            entry<Route.RecipeDetail> { key ->
                RecipeDetailRoot(key.recipeId)
            }
        }
    )
}
