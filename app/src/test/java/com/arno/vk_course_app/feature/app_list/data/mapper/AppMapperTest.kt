package com.arno.vk_course_app.feature.app_list.data.mapper

import com.arno.vk_course_app.feature.app_list.data.dto.AppDetailsDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test
import java.lang.reflect.Modifier

class AppMapperTest {

        @Test
        fun `toDomain with fully filled dto EXPECT all fields mapped`() {
                val dto = dto(
                        developer = "a", ageRating = 5, size = 1.0,
                        screenshotUrlList = listOf("scr1", "scr2")
                )

                val result = dto.toDomain()

                assertEquals(dto.id, result.id)
                assertEquals(dto.name, result.name)
                assertEquals(dto.developer, result.developer)
                assertEquals(dto.category, result.category)
                assertEquals(dto.ageRating, result.ageRating)
                assertEquals(dto.size, result.size, 0.001)
                assertEquals(dto.iconUrl, result.iconUrl)
                assertEquals(dto.screenshotUrlList, result.screenshotUrlList)
                assertEquals(dto.description, result.description)
        }

        @Test
        fun `toDomain EXPECT isInWishlist always false`() {
                assertFalse(dto().toDomain().isInWishlist)
        }

        @Test
        fun `toDomain with all dto defaults EXPECT defaults propagated to domain`() {
                val result = dto().toDomain()

                assertEquals("", result.developer)
                assertEquals(0, result.ageRating)
                assertEquals(0.0, result.size, 0.001)
                assertNull(result.screenshotUrlList)
        }

        @Test
        fun `toDomain with empty strings and extreme numbers EXPECT values preserved`() {
                val dto = dto(
                        id = "", name = "", category = "", iconUrl = "", description = "",
                        ageRating = Int.MAX_VALUE, size = Double.MAX_VALUE,
                        screenshotUrlList = emptyList()
                )

                val result = dto.toDomain()

                assertEquals(dto.id, result.id)
                assertEquals(dto.name, result.name)
                assertEquals(dto.ageRating, result.ageRating)
                assertEquals(dto.size, result.size, 0.0)
        }

        @Test
        fun `dto field count EXPECT 9 to detect new unmapped fields`() {
                val count = AppDetailsDto::class.java.declaredFields
                        .count { !Modifier.isStatic(it.modifiers) && !it.name.startsWith("$") }

                assertEquals("new field in AppDetailsDto — update toDomain", 9, count)
        }

        private fun dto(
                id: String = "1",
                name: String = "name",
                developer: String = "",
                category: String = "cat",
                ageRating: Int = 0,
                size: Double = 0.0,
                iconUrl: String = "url",
                screenshotUrlList: List<String>? = null,
                description: String = "desc",
        ) = AppDetailsDto(
                id = id, name = name, developer = developer, category = category,
                ageRating = ageRating, size = size, iconUrl = iconUrl,
                screenshotUrlList = screenshotUrlList, description = description,
        )
}
