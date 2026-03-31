package com.arno.vk_course_app.feature.app_list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AppDetailsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
        abstract fun appDetailsDao(): AppDetailsDao
}
