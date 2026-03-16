package com.arno.vk_course_app.feature.app_list.presentation

sealed interface AppListEvent {
        data object ShowLogoSnackbar : AppListEvent
}
