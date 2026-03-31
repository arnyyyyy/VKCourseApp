package com.arno.vk_course_app.feature.app_list.data.repository

import com.arno.vk_course_app.feature.app_list.data.api.CatalogApi
import com.arno.vk_course_app.feature.app_list.data.mapper.toDomain
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
        private val api: CatalogApi,
) : AppRepository {

        override suspend fun getApps(): List<AppDetails> {
                return api.getCatalog().map { it.toDomain() }
        }

        override suspend fun getAppById(id: String): AppDetails? {
                return try {
                        api.getAppById(id).toDomain()
                } catch (_: Exception) {
                        null
                }
        }
}
