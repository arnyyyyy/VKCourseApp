package com.arno.vk_course_app.feature.app_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arno.vk_course_app.feature.app_details.data.AppDetails

class AppDetailsViewModelFactory(
    private val appDetails: AppDetails,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppDetailsViewModel(appDetails) as T
    }
}

