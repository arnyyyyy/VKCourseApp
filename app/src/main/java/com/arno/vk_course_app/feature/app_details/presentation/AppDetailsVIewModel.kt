package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppDetailsViewModel(
        private val appId: String,
        private val repository: AppRepository,
) : ViewModel() {

        private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
        val state = _state.asStateFlow()

        init {
                viewModelScope.launch {
                        try {
                                _state.value = AppDetailsState.Loading
                                val app = repository.getAppById(appId)
                                if (app != null) {
                                        _state.value = AppDetailsState.Content(appDetails = app)
                                } else {
                                        _state.value = AppDetailsState.Error
                                }
                        } catch (_: Exception) {
                                _state.value = AppDetailsState.Error
                        }
                }
        }

        fun collapseDescription() {
                _state.update { currentState ->
                        if (currentState is AppDetailsState.Content) {
                                currentState.copy(descriptionCollapsed = true)
                        } else {
                                currentState
                        }
                }
        }
}