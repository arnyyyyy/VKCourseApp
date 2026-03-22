package com.arno.vk_course_app.feature.app_details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arno.vk_course_app.R
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsState
import com.arno.vk_course_app.feature.app_details.presentation.AppDetailsViewModel

@Composable
fun AppDetailsScreen(
        onBackClick: () -> Unit,
) {
        val viewModel: AppDetailsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        Scaffold(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) { contentPadding ->
                when (val currentState = state) {
                        is AppDetailsState.Content -> {
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

                        AppDetailsState.Loading -> {
                                Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center,
                                ) {
                                        CircularProgressIndicator()
                                }
                        }

                        AppDetailsState.Error -> {
                                Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center,
                                ) {
                                        Text(text = stringResource(R.string.app_list_error))
                                }
                        }
                }
        }
}