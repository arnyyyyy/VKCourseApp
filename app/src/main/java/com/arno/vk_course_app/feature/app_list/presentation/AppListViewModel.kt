package com.arno.vk_course_app.feature.app_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arno.vk_course_app.feature.app_list.data.AppListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
        private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
        val state: StateFlow<AppListState> = _state.asStateFlow()

        private val _pendingSnackbars = MutableStateFlow(0)
        val pendingSnackbars: StateFlow<Int> = _pendingSnackbars.asStateFlow()

        init {
                viewModelScope.launch {
                        runCatching {
                                _state.value = AppListState.Loading
                                val apps = AppListRepository.getAppList()
                                _state.value = AppListState.Content(apps)
                        }.onFailure {
                                _state.value = AppListState.Error
                        }
                }
        }

        fun onLogoClick() {
                _pendingSnackbars.update { it + 1 } // здесь легкая операция, поэтому  норм делать не в корутине
        }

        fun onSnackbarShown() {
                _pendingSnackbars.update { maxOf(0, it - 1) }
        }
}
