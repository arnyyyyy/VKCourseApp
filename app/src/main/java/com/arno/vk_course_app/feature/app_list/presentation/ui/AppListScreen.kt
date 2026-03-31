package com.arno.vk_course_app.feature.app_list.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arno.vk_course_app.feature.app_details.data.AppDetails
import com.arno.vk_course_app.feature.app_list.data.AppListRepository

private val ToolbarBlue = Color(0xFF4C75A3)

@Composable
fun AppListScreen(
        onAppClick: (AppDetails) -> Unit,
        modifier: Modifier = Modifier,
) {
        val apps = AppListRepository.getAppList()

        Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = { AppListToolbar() },
                containerColor = ToolbarBlue,
        ) { innerPadding ->
                AppList(
                        apps = apps,
                        onAppClick = onAppClick,
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(top = innerPadding.calculateTopPadding()),
                )
        }
}
