package com.arno.vk_course_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arno.vk_course_app.feature.app_details.presentation.ui.AppDetailsScreen
import com.arno.vk_course_app.feature.app_list.presentation.ui.AppListScreen
import kotlinx.serialization.Serializable

@Serializable
object AppListRoute

@Serializable
data class AppDetailsRoute(val appId: String)

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        NavHost(
                navController = navController,
                startDestination = AppListRoute,
                modifier = modifier,
        ) {
                composable<AppListRoute> {
                        AppListScreen(
                                onAppClick = { app ->
                                        navController.navigate(AppDetailsRoute(appId = app.id))
                                },
                        )
                }
                composable<AppDetailsRoute> {
                        AppDetailsScreen(
                                onBackClick = { navController.popBackStack() },
                        )
                }
        }
}
