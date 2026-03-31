package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import javax.inject.Inject

class GetAppUseCase @Inject constructor(
        private val repository: AppRepository,
) {
        suspend operator fun invoke(): List<AppDetails> {
                return repository.getApps()
        }
}
