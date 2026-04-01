package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arno.vk_course_app.feature.app_list.domain.usecase.GetAppByIdUseCase
import com.arno.vk_course_app.feature.app_list.domain.usecase.ObserveAppDetailsUseCase
import com.arno.vk_course_app.feature.app_list.domain.usecase.ToggleWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
        savedStateHandle: SavedStateHandle,
        private val getAppByIdUseCase: GetAppByIdUseCase,
        private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
        private val toggleWishlistUseCase: ToggleWishlistUseCase,
) : ViewModel() {

        private val appId: String = checkNotNull(savedStateHandle["appId"])

        private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
        val state = _state.asStateFlow()

        init {
                loadAppDetails()
                observeAppDetails()
        }

        private fun loadAppDetails() {
                viewModelScope.launch {
                        try {
                                _state.value = AppDetailsState.Loading
                                val app = getAppByIdUseCase(appId)
                                if (app == null) {
                                        _state.value = AppDetailsState.Error
                                }
                        } catch (_: Exception) {
                                _state.value = AppDetailsState.Error
                        }
                }
        }

        private fun observeAppDetails() {
                viewModelScope.launch {
                        observeAppDetailsUseCase(appId)
                                .catch { e -> _state.value = AppDetailsState.Error }
                                .collect { appDetails ->
                                        _state.value = AppDetailsState.Content(
                                                appDetails = appDetails,
                                                descriptionCollapsed = (_state.value as? AppDetailsState.Content)?.descriptionCollapsed ?: false,
                                        )
                                }
                }
        }

        fun toggleWishlist() {
                viewModelScope.launch {
                        toggleWishlistUseCase(appId)
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