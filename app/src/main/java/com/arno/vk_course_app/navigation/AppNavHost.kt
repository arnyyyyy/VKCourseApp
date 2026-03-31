package com.arno.vk_course_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsViewModelFactory
import com.arno.vk_course_app.feature.app_details.presentation.ui.AppDetailsScreen
import com.arno.vk_course_app.feature.app_list.data.repository.AppRepositoryImpl
import com.arno.vk_course_app.feature.app_list.domain.usecase.GetAppByIdUseCase
import com.arno.vk_course_app.feature.app_list.domain.usecase.GetAppsUseCase
import com.arno.vk_course_app.feature.app_list.presentation.AppListViewModelFactory
import com.arno.vk_course_app.feature.app_list.presentation.ui.AppListScreen
import kotlinx.serialization.Serializable

@Serializable
object AppListRoute

@Serializable
data class AppDetailsRoute(val appId: String)

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val repository = remember { AppRepositoryImpl() }
        val getAppsUseCase = remember { GetAppsUseCase(repository) }
        val getAppByIdUseCase = remember { GetAppByIdUseCase(repository) }

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
                                viewModelFactory = AppListViewModelFactory(getAppsUseCase),
                        )
                }
                composable<AppDetailsRoute> { backStackEntry ->
                        val route = backStackEntry.toRoute<AppDetailsRoute>()
                        AppDetailsScreen(
                                viewModelFactory = AppDetailsViewModelFactory(route.appId, getAppByIdUseCase),
                                onBackClick = {
                                        navController.popBackStack(
                                                route = AppListRoute,
                                                inclusive = false
                                        )
                                }
                        )
                }
        }
}
