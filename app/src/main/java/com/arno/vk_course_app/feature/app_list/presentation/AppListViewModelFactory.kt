package com.arno.vk_course_app.feature.app_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arno.vk_course_app.feature.app_list.data.repository.AppRepositoryImpl

class AppListViewModelFactory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppListViewModel(repository = AppRepositoryImpl()) as T
        }
}
