package com.arno.vk_course_app.feature.app_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arno.vk_course_app.feature.app_list.data.AppListRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
        private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
        val state: StateFlow<AppListState> = _state.asStateFlow()

        private val _event = Channel<AppListEvent>()
        val event: Flow<AppListEvent> = _event.receiveAsFlow()

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
                viewModelScope.launch {
                        _event.send(AppListEvent.ShowLogoSnackbar)
                }
        }
}
