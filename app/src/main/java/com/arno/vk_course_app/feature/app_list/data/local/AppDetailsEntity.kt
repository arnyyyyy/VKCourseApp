package com.arno.vk_course_app.feature.app_list.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_details")
data class AppDetailsEntity(
        @PrimaryKey
        val id: String,
        val name: String,
        val developer: String,
        val category: String,
        val ageRating: Int,
        val size: Double,
        val iconUrl: String,
        val screenshotUrlList: List<String>?,
        val description: String,
)
