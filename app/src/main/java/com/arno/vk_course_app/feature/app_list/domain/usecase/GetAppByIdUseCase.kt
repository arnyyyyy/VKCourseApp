package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository

class GetAppByIdUseCase(
        private val repository: AppRepository,
) {
        suspend operator fun invoke(id: String): AppDetails? {
                return repository.getAppById(id)
        }
}
