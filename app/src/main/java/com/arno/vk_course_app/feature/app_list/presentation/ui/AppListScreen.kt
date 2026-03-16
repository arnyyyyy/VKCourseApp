package com.arno.vk_course_app.feature.app_list.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arno.vk_course_app.R
import com.arno.vk_course_app.feature.app_details.data.AppDetails
import com.arno.vk_course_app.feature.app_list.presentation.AppListEvent
import com.arno.vk_course_app.feature.app_list.presentation.AppListState
import com.arno.vk_course_app.feature.app_list.presentation.AppListViewModel

private val ToolbarBlue = Color(0xFF4C75A3)

@Composable
fun AppListScreen(
        onAppClick: (AppDetails) -> Unit,
        modifier: Modifier = Modifier,
        viewModel: AppListViewModel = viewModel(),
) {
        val state by viewModel.state.collectAsStateWithLifecycle()

        val snackbarHostState = remember { SnackbarHostState() }
        val snackbarText = stringResource(id = R.string.snackbar_text)

        LaunchedEffect(Unit) {
                viewModel.event.collect { event ->
                        when (event) {
                                AppListEvent.ShowLogoSnackbar -> snackbarHostState.showSnackbar(snackbarText)
                        }
                }
        }

        Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = { AppListToolbar(onLogoClick = viewModel::onLogoClick) },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                containerColor = ToolbarBlue,
        ) { innerPadding ->
                when (val currentState = state) {
                        is AppListState.Content -> {
                                AppList(
                                        apps = currentState.apps,
                                        onAppClick = onAppClick,
                                        modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = innerPadding.calculateTopPadding()),
                                )
                        }

                        AppListState.Error -> {
                                Box(
                                        modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = innerPadding.calculateTopPadding()),
                                        contentAlignment = Alignment.Center,
                                ) {
                                        Text(text = stringResource(id = R.string.app_list_error))
                                }
                        }

                        AppListState.Loading -> {
                                Box(
                                        modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = innerPadding.calculateTopPadding()),
                                        contentAlignment = Alignment.Center,
                                ) {
                                        CircularProgressIndicator()
                                }
                        }
                }
        }
}
