package com.arno.vk_course_app.feature.app_list.data.mapper

import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsEntity
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import javax.inject.Inject

class AppDetailsEntityMapper @Inject constructor() {
        fun toDomain(entity: AppDetailsEntity): AppDetails {
                return AppDetails(
                        id = entity.id,
                        name = entity.name,
                        developer = entity.developer,
                        category = entity.category,
                        ageRating = entity.ageRating,
                        size = entity.size,
                        iconUrl = entity.iconUrl,
                        screenshotUrlList = entity.screenshotUrlList,
                        description = entity.description,
                        isInWishlist = entity.isInWishlist,
                )
        }

        fun toEntity(domain: AppDetails): AppDetailsEntity {
                return AppDetailsEntity(
                        id = domain.id,
                        name = domain.name,
                        developer = domain.developer,
                        category = domain.category,
                        ageRating = domain.ageRating,
                        size = domain.size,
                        iconUrl = domain.iconUrl,
                        screenshotUrlList = domain.screenshotUrlList,
                        description = domain.description,
                        isInWishlist = domain.isInWishlist,
                )
        }
}
