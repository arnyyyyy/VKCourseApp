package com.arno.vk_course_app.di

import com.arno.vk_course_app.feature.app_list.data.repository.AppRepositoryImpl
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
        @Binds
        @Singleton
        abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}

