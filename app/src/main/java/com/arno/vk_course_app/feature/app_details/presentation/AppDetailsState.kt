package com.arno.vk_course_app.feature.app_details.presentation

import androidx.compose.runtime.Immutable
import com.arno.vk_course_app.feature.app_details.data.AppDetails

@Immutable
sealed interface AppDetailsState {
    data class Content(
        val appDetails: AppDetails,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}