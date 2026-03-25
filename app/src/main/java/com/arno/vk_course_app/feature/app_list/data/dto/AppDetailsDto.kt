package com.arno.vk_course_app.feature.app_list.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto(
        val id: String,
        val name: String,
        val developer: String = "",
        val category: String,
        val ageRating: Int = 0,
        val size: Double = 0.0,
        val iconUrl: String,
        val screenshotUrlList: List<String>? = null,
        val description: String,
)