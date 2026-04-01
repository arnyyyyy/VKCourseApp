package com.arno.vk_course_app.feature.app_list.domain.model

data class AppDetails(
        val id: String,
        val name: String,
        val developer: String = "",
        val category: String,
        val ageRating: Int = 0,
        val size: Double = 0.0,
        val iconUrl: String,
        val screenshotUrlList: List<String>? = null,
        val description: String,
        val isInWishlist: Boolean = false,
)