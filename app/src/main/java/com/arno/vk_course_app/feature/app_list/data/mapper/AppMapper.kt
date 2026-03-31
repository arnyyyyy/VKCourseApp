package com.arno.vk_course_app.feature.app_list.data.mapper

import com.arno.vk_course_app.feature.app_list.data.dto.AppDetailsDto
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.model.Category

fun AppDetailsDto.toDomain(): AppDetails {
        return AppDetails(
                id = id,
                name = name,
                developer = developer,
                category = try {
                        Category.valueOf(category)
                } catch (_: IllegalArgumentException) {
                        Category.APP
                },
                ageRating = ageRating,
                size = size,
                iconUrl = iconUrl,
                screenshotUrlList = screenshotUrlList,
                description = description
        )
}
