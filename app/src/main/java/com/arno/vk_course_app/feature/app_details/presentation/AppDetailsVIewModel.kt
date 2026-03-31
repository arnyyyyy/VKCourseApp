package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.ViewModel
import com.arno.vk_course_app.feature.app_details.data.AppDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppDetailsViewModel(appDetails: AppDetails) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(
        AppDetailsState.Content(
            appDetails = appDetails,
            descriptionCollapsed = false,
        )
    )
    val state = _state.asStateFlow()


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