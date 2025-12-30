package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MainScreen(
    body: @Composable (modifier: Modifier) -> Unit,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    val currentRoute = backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Spacer(Modifier.height(14.dp))

                NavigationBar(
                    modifier = Modifier.height(106.dp),
                    containerColor = AppColors.white,
                    tonalElevation = 8.dp,
                ) {
                    NavigationBarItem(
                        selected = currentRoute is Route.Home,
                        onClick = {
                            backStack.clear()
                            backStack.add(Route.Home)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.home_2),
                                contentDescription = "Home",
                                tint = if (currentRoute == Route.Home) AppColors.primary100 else AppColors.gray4
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {},
                        alwaysShowLabel = false
                    )
                    NavigationBarItem(
                        selected = currentRoute is Route.SavedRecipes,
                        onClick = {
                            backStack.clear()
                            backStack.add(Route.SavedRecipes)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.union),
                                contentDescription = "Saved Recipes",
                                tint = if (currentRoute == Route.SavedRecipes) AppColors.primary100 else AppColors.gray4
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {},
                        alwaysShowLabel = false
                    )

                    Box(modifier = Modifier.offset(y = (-18).dp)) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(AppColors.primary100, CircleShape)
                                .clickable {
                                    // TODO 플러스 버튼 클릭 이벤트 처리
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_add),
                                contentDescription = "Add",
                                tint = Color.White
                            )
                        }
                    }

                    NavigationBarItem(
                        selected = currentRoute is Route.Notifications,
                        onClick = {
                            backStack.clear()
                            backStack.add(Route.Notifications)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.notification_bing),
                                contentDescription = "Notification",
                                tint = if (currentRoute == Route.Notifications) AppColors.primary100 else AppColors.gray4
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {},
                        alwaysShowLabel = false
                    )
                    NavigationBarItem(
                        selected = currentRoute is Route.Profile,
                        onClick = {
                            backStack.clear()
                            backStack.add(Route.Profile)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.profile_img),
                                contentDescription = "Profile",
                                tint = if (currentRoute == Route.Profile) AppColors.primary100 else AppColors.gray4
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {},
                        alwaysShowLabel = false
                    )
                }
            }
        }
    ) { innerPadding ->
        body(modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    @Composable
    fun Test(modifier: Modifier = Modifier) {
        Column {
            Text("Test")
        }
    }

    val backStack = NavBackStack<NavKey>().apply { add(Route.SavedRecipes) }

    MainScreen(
        body = { modifier -> Test(modifier) },
        backStack = backStack
    )
}
