package com.arno.vk_course_app.di

import android.content.Context
import androidx.room.Room
import com.arno.vk_course_app.feature.app_list.data.local.AppDatabase
import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
                return Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                ).build()
        }

        @Provides
        @Singleton
        fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
                return database.appDetailsDao()
        }
}
