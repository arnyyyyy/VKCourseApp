package com.arno.vk_course_app.feature.app_list.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

        @TypeConverter
        fun fromStringList(value: List<String>?): String? {
                return value?.let { Json.encodeToString(it) }
        }

        @TypeConverter
        fun toStringList(value: String?): List<String>? {
                return value?.let { Json.decodeFromString<List<String>>(it) }
        }
}
