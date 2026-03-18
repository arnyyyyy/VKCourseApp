package com.arno.vk_course_app.feature.app_list.data.dto

data class AppDetailsDto(
        val id: String,
        val name: String,
        val developer: String,
        val category: String,
        val ageRating: Int,
        val size: Float,
        val iconUrl: String,
        val screenshotUrlList: List<String>?,
        val description: String,
)