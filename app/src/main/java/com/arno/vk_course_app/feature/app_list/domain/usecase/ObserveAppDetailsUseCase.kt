package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppDetailsUseCase @Inject constructor(
        private val repository: AppRepository,
) {
        operator fun invoke(id: String): Flow<AppDetails> {
                return repository.observeAppDetails(id)
        }
}
