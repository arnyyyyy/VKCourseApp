package com.arno.vk_course_app.feature.app_details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arno.vk_course_app.feature.app_details.data.AppDetails
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsState
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsViewModel
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsViewModelFactory

@Composable
fun AppDetailsScreen(
    appDetails: AppDetails,
    onBackClick: () -> Unit,
) {
    val viewModel: AppDetailsViewModel = viewModel(
        factory = AppDetailsViewModelFactory(appDetails)
    )
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        val currentState = state
        if (currentState is AppDetailsState.Content) {
            AppDetailsContent(
                content = currentState,
                onBackClick = onBackClick,
                onShareClick = {},
                onInstallClick = {},
                onReadMoreClick = { viewModel.collapseDescription() },
                onDeveloperClick = {},
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(contentPadding),
            )
        }
    }
}