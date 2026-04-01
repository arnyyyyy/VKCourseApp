package com.arno.vk_course_app.feature.app_list.domain.repository

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import kotlinx.coroutines.flow.Flow

interface AppRepository {
        suspend fun getApps(): List<AppDetails>
        suspend fun getAppById(id: String): AppDetails?
        fun observeAppDetails(id: String): Flow<AppDetails>
        suspend fun toggleWishlist(id: String)
}
