package com.arno.vk_course_app.feature.app_list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDetailsDao {
        @Query("SELECT * FROM app_details WHERE id = :id")
        fun getAppDetails(id: String): Flow<AppDetailsEntity?>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAppDetails(entity: AppDetailsEntity)

        @Query("UPDATE app_details SET isInWishlist = :isInWishlist WHERE id = :id")
        suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean)
}
