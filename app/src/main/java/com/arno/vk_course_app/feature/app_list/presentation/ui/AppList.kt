package com.arno.vk_course_app.feature.app_list.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.model.toText

@Composable
fun AppList(
        apps: List<AppDetails>,
        onAppClick: (AppDetails) -> Unit,
        modifier: Modifier = Modifier,
) {
        LazyColumn(
                modifier = modifier
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

@Composable
private fun AppListItem(
        app: AppDetails,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
) {
        Row(
                modifier = modifier
                        .fillMaxWidth()
                        .clickable(onClick = onClick)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
        ) {
                AsyncImage(
                        model = app.iconUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                        Text(
                                text = app.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                                text = app.description,
                                fontWeight = FontWeight(300),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier.height(4.dp))
                        Text(
                                text = app.category.toText(),
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                        )
                }
        }
}
