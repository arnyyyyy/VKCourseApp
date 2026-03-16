package com.arno.vk_course_app.feature.app_list.presentation

import com.arno.vk_course_app.feature.app_details.data.AppDetails

sealed interface AppListState {
        data class Content(
                val apps: List<AppDetails> = emptyList()
        ) : AppListState

        object Loading : AppListState
        object Error : AppListState
}
