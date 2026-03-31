package com.arno.vk_course_app.feature.app_list.domain.repository

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails

interface AppRepository {
        suspend fun getApps(): List<AppDetails>
        suspend fun getAppById(id: String): AppDetails?
}
