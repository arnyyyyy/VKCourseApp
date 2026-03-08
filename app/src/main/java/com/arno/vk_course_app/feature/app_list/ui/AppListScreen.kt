package com.arno.vk_course_app.feature.app_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arno.vk_course_app.feature.app_details.data.AppDetails
import com.arno.vk_course_app.feature.app_list.data.AppListRepository

private val ToolbarBlue = Color(0xFF4C75A3)

@Composable
fun AppListScreen(
        onAppClick: (AppDetails) -> Unit,
        modifier: Modifier = Modifier,
) {
        val apps = AppListRepository.getAppList()

        Box(
                modifier = modifier
                        .fillMaxSize()
                        .background(ToolbarBlue),
        ) {
                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .statusBarsPadding(),
                ) {
                        AppListToolbar()

                        LazyColumn(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                                        .background(Color.White),
                        ) {
                                items(
                                        items = apps,
                                        key = { it.id },
                                ) { app ->
                                        AppListItem(
                                                app = app,
                                                onClick = { onAppClick(app) },
                                        )
                                        HorizontalDivider(modifier = Modifier.alpha(0.7f))
                                }
                        }
                }
        }
}
