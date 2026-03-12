package com.arno.vk_course_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arno.vk_course_app.feature.app_details.presentation.ui.AppDetailsScreen
import com.arno.vk_course_app.feature.app_list.data.AppListRepository
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
                composable<AppDetailsRoute> { backStackEntry ->
                        val route = backStackEntry.toRoute<AppDetailsRoute>()
                        val appDetails = AppListRepository.findById(route.appId)
                        if (appDetails != null) {
                                AppDetailsScreen(
                                        appDetails = appDetails,
                                        onBackClick = { navController.popBackStack() },
                                )
                        }
                }
        }
}
