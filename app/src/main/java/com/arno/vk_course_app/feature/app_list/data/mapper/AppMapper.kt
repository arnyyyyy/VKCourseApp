package com.arno.vk_course_app.feature.app_list.data.mapper

import com.arno.vk_course_app.feature.app_list.data.dto.AppDetailsDto
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails

fun AppDetailsDto.toDomain(): AppDetails {
        return AppDetails(
                id = id,
                name = name,
                developer = developer,
                category = category,
                ageRating = ageRating,
                size = size,
                iconUrl = iconUrl,
                screenshotUrlList = screenshotUrlList,
                description = description
        )
}
