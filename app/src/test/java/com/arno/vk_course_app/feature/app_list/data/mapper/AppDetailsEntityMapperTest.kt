package com.arno.vk_course_app.feature.app_list.data.mapper

import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsEntity
import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Modifier

class AppDetailsEntityMapperTest {

        private lateinit var mapper: AppDetailsEntityMapper

        @Before
        fun setUp() {
                mapper = AppDetailsEntityMapper()
        }

        @Test
        fun `toDomain with fully filled entity EXPECT all fields mapped`() {
                val e = entity(
                        developer = "a", ageRating = 5, size = 1.0,
                        screenshots = listOf("a", "b"), isInWishlist = true
                )

                val result = mapper.toDomain(e)

                assertEquals(e.id, result.id)
                assertEquals(e.name, result.name)
                assertEquals(e.developer, result.developer)
                assertEquals(e.category, result.category)
                assertEquals(e.ageRating, result.ageRating)
                assertEquals(e.size, result.size, 0.001)
                assertEquals(e.iconUrl, result.iconUrl)
                assertEquals(e.screenshotUrlList, result.screenshotUrlList)
                assertEquals(e.description, result.description)
                assertTrue(result.isInWishlist)
        }

        @Test
        fun `toDomain with isInWishlist false EXPECT false`() {
                assertFalse(mapper.toDomain(entity()).isInWishlist)
        }

        @Test
        fun `toDomain with null screenshot list EXPECT null`() {
                assertNull(mapper.toDomain(entity()).screenshotUrlList)
        }

        @Test
        fun `toDomain with empty screenshot list EXPECT empty list`() {
                assertEquals(
                        emptyList<String>(),
                        mapper.toDomain(entity(screenshots = emptyList())).screenshotUrlList
                )
        }


        @Test
        fun `toEntity with fully filled domain EXPECT all fields mapped`() {
                val d = domain(
                        developer = "a", ageRating = 5, size = 1.0,
                        screenshots = listOf("a", "b"), isInWishlist = true
                )

                val result = mapper.toEntity(d)

                assertEquals(d.id, result.id)
                assertEquals(d.name, result.name)
                assertEquals(d.developer, result.developer)
                assertEquals(d.category, result.category)
                assertEquals(d.ageRating, result.ageRating)
                assertEquals(d.size, result.size, 0.001)
                assertEquals(d.iconUrl, result.iconUrl)
                assertEquals(d.screenshotUrlList, result.screenshotUrlList)
                assertEquals(d.description, result.description)
                assertTrue(result.isInWishlist)
        }

        @Test
        fun `toEntity with isInWishlist false EXPECT false`() {
                assertFalse(mapper.toEntity(domain()).isInWishlist)
        }

        @Test
        fun `toEntity with null screenshot list EXPECT null`() {
                assertNull(mapper.toEntity(domain()).screenshotUrlList)
        }

        @Test
        fun `roundtrip domain to entity to domain EXPECT data preserved`() {
                val original = domain(
                        developer = "a", ageRating = 18, size = 1.5,
                        screenshots = listOf("a", "b"), isInWishlist = true
                )

                assertEquals(original, mapper.toDomain(mapper.toEntity(original)))
        }

        @Test
        fun `roundtrip entity to domain to entity EXPECT data preserved`() {
                val original = entity(
                        developer = "a", ageRating = 3, size = 0.5,
                        screenshots = listOf("a"), isInWishlist = true
                )

                assertEquals(original, mapper.toEntity(mapper.toDomain(original)))
        }


        @Test
        fun `toDomain with extreme values EXPECT no data loss`() {
                val e = entity(
                        id = "", name = "A".repeat(500),
                        ageRating = Int.MAX_VALUE, size = Double.MIN_VALUE,
                        screenshots = (1..100).map { "$it" }).copy(
                        description = "Описание с\nпереносами\tтабами и эмодзи \uD83C\uDF45 · \uD83C\uDF47 · \uD83C\uDF48  · \uD83C\uDF49",
                )

                val result = mapper.toDomain(
                        e
                )

                assertEquals(e.name, result.name)
                assertEquals(e.ageRating, result.ageRating)
                assertEquals(e.size, result.size, 0.0)
                assertEquals(e.screenshotUrlList?.size, result.screenshotUrlList?.size)
                assertEquals(e.description, result.description)
        }


        @Test
        fun `entity field count EXPECT 10 to detect new unmapped fields`() {
                val count = AppDetailsEntity::class.java.declaredFields
                        .count { !Modifier.isStatic(it.modifiers) && !it.name.startsWith("$") }

                assertEquals("new field in AppDetailsEntity — update mapper", 10, count)
        }

        @Test
        fun `domain field count EXPECT 10 to detect new unmapped fields`() {
                val count = AppDetails::class.java.declaredFields
                        .count { !Modifier.isStatic(it.modifiers) && !it.name.startsWith("$") }

                assertEquals("new field in AppDetails — update mapper", 10, count)
        }


        private fun entity(
                id: String = "1",
                name: String = "name",
                developer: String = "dev",
                category: String = "cat",
                ageRating: Int = 0,
                size: Double = 0.0,
                iconUrl: String = "url",
                screenshots: List<String>? = null,
                description: String = "desc",
                isInWishlist: Boolean = false,
        ) = AppDetailsEntity(
                id = id, name = name, developer = developer, category = category,
                ageRating = ageRating, size = size, iconUrl = iconUrl,
                screenshotUrlList = screenshots, description = description,
                isInWishlist = isInWishlist,
        )

        private fun domain(
                id: String = "1",
                name: String = "name",
                developer: String = "dev",
                category: String = "cat",
                ageRating: Int = 0,
                size: Double = 0.0,
                iconUrl: String = "url",
                screenshots: List<String>? = null,
                description: String = "desc",
                isInWishlist: Boolean = false,
        ) = AppDetails(
                id = id, name = name, developer = developer, category = category,
                ageRating = ageRating, size = size, iconUrl = iconUrl,
                screenshotUrlList = screenshots, description = description,
                isInWishlist = isInWishlist,
        )
}
