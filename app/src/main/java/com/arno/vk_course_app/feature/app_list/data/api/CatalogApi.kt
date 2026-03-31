package com.arno.vk_course_app.feature.app_list.data.api

import com.arno.vk_course_app.feature.app_list.data.dto.AppDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {
        @GET("catalog")
        suspend fun getCatalog(): List<AppDetailsDto>

        @GET("catalog/{id}")
        suspend fun getAppById(@Path("id") id: String): AppDetailsDto
}
