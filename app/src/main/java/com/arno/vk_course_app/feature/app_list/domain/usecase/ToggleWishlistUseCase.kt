package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import javax.inject.Inject

class ToggleWishlistUseCase @Inject constructor(
        private val repository: AppRepository,
) {
        suspend operator fun invoke(id: String) {
                repository.toggleWishlist(id)
        }
}
