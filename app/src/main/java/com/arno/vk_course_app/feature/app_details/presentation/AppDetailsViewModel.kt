package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arno.vk_course_app.feature.app_list.domain.usecase.GetAppByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
        savedStateHandle: SavedStateHandle,
        private val getAppByIdUseCase: GetAppByIdUseCase,
) : ViewModel() {

        private val appId: String = checkNotNull(savedStateHandle["appId"])

        private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
        val state = _state.asStateFlow()

        init {
                viewModelScope.launch {
                        try {
                                _state.value = AppDetailsState.Loading
                                val app = getAppByIdUseCase(appId)
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