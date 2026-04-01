package com.arno.vk_course_app.feature.app_list.data.repository

import android.util.Log
import com.arno.vk_course_app.feature.app_list.data.api.CatalogApi
import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsDao
import com.arno.vk_course_app.feature.app_list.data.mapper.AppDetailsEntityMapper
import com.arno.vk_course_app.feature.app_list.data.mapper.toDomain
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
        private val api: CatalogApi,
        private val dao: AppDetailsDao,
        private val entityMapper: AppDetailsEntityMapper,
) : AppRepository {

        override suspend fun getApps(): List<AppDetails> {
                return api.getCatalog().map { it.toDomain() }
        }

        override suspend fun getAppById(id: String): AppDetails? {
                val entity = dao.getAppDetails(id).first()
                return if (entity != null) {
                        entityMapper.toDomain(entity)
                } else {
                        try {
                                val dto = api.getAppById(id)
                                val domainModel = dto.toDomain()

                                withContext(Dispatchers.IO) {
                                        dao.insertAppDetails(entityMapper.toEntity(domainModel))
                                }

                                domainModel
                        } catch (e: Exception) {
                                Log.e("AppRepositoryImpl", "Failed to fetch app details for id=$id", e)
                                null
                        }
                }
        }
}
