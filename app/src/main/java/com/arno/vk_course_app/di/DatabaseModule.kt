package com.arno.vk_course_app.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

        private val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                        db.execSQL("ALTER TABLE app_details ADD COLUMN isInWishlist INTEGER NOT NULL DEFAULT 0")
                }
        }

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
                return Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                ).addMigrations(MIGRATION_1_2).build()
        }

        @Provides
        @Singleton
        fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
                return database.appDetailsDao()
        }
}
