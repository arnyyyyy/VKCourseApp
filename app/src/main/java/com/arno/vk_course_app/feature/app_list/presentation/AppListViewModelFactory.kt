package com.arno.vk_course_app.feature.app_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arno.vk_course_app.feature.app_list.domain.usecase.GetAppsUseCase

class AppListViewModelFactory(private val getAppsUseCase: GetAppsUseCase) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppListViewModel(getAppsUseCase = getAppsUseCase) as T
        }
}
