package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository

class AppDetailsViewModelFactory(
        private val appId: String,
        private val repository: AppRepository,
) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppDetailsViewModel(appId = appId, repository = repository) as T
        }
}
