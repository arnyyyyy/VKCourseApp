package com.arno.vk_course_app.feature.app_list.data.repository

import com.arno.vk_course_app.feature.app_list.data.api.CatalogApi
import com.arno.vk_course_app.feature.app_list.data.dto.AppDetailsDto
import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsDao
import com.arno.vk_course_app.feature.app_list.data.local.AppDetailsEntity
import com.arno.vk_course_app.feature.app_list.data.mapper.AppDetailsEntityMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AppRepositoryImplTest {

        private lateinit var api: CatalogApi
        private lateinit var dao: AppDetailsDao
        private lateinit var entityMapper: AppDetailsEntityMapper
        private lateinit var repository: AppRepositoryImpl

        @Before
        fun setUp() {
                api = mock()
                dao = mock()
                entityMapper = AppDetailsEntityMapper()
                repository = AppRepositoryImpl(api, dao, entityMapper)
        }

        @Test
        fun `getApps with non-empty catalog EXPECT mapped to domain saving order`() = runTest {
                val dtos = listOf(dto(name = "a"), dto(name = "b"), dto(name = "c"))
                whenever(api.getCatalog()).thenReturn(dtos)

                val result = repository.getApps()

                assertEquals(dtos.size, result.size)
                assertEquals(dtos[0].name, result[0].name)
                assertEquals(dtos[1].name, result[1].name)
                assertEquals(dtos[2].name, result[2].name)
        }

        @Test
        fun `getApps with empty catalog EXPECT empty list`() = runTest {
                whenever(api.getCatalog()).thenReturn(emptyList())

                assertTrue(repository.getApps().isEmpty())
        }

        @Test(expected = RuntimeException::class)
        fun `getApps when api throws EXPECT exception propagated`() = runTest {
                whenever(api.getCatalog()).thenThrow(RuntimeException("err"))

                repository.getApps()
        }

        @Test
        fun `getApps EXPECT all dto fields correctly mapped to domain`() = runTest {
                val dto = dto(
                        developer = "dev", ageRating = 5, size = 1.0,
                        screenshotUrlList = listOf("scr1", "scr2")
                )
                whenever(api.getCatalog()).thenReturn(listOf(dto))

                val result = repository.getApps().first()

                assertEquals(dto.name, result.name)
                assertEquals(dto.developer, result.developer)
                assertEquals(dto.ageRating, result.ageRating)
                assertEquals(dto.size, result.size, 0.001)
                assertEquals(dto.screenshotUrlList, result.screenshotUrlList)
        }

        @Test
        fun `getAppById when entity cached EXPECT no api call`() = runTest {
                val cached = entity(isInWishlist = true)
                whenever(dao.getAppDetails(cached.id)).thenReturn(flowOf(cached))

                val result = repository.getAppById(cached.id)

                assertEquals(cached.name, result?.name)
                assertTrue(result?.isInWishlist == true)
                verify(api, never()).getAppById(any())
        }

        @Test
        fun `getAppById when no cache EXPECT fetched from api and saved to dao`() = runTest {
                val dto = dto()
                whenever(dao.getAppDetails(dto.id)).thenReturn(flowOf(null))
                whenever(api.getAppById(dto.id)).thenReturn(dto)

                val result = repository.getAppById(dto.id)

                assertEquals(dto.name, result?.name)
                verify(dao).insertAppDetails(any())
        }

        @Test
        fun `getAppById when no cache and api throws EXPECT null`() = runTest {
                val d = dto()
                whenever(dao.getAppDetails(d.id)).thenReturn(flowOf(null))
                whenever(api.getAppById(d.id)).thenThrow(RuntimeException("err"))

                assertNull(repository.getAppById(d.id))
        }

        @Test
        fun `getAppById with cached entity full data EXPECT all fields got properly`() = runTest {
                val cached = entity(developer = "a", isInWishlist = true)
                whenever(dao.getAppDetails(cached.id)).thenReturn(flowOf(cached))

                val result = requireNotNull(repository.getAppById(cached.id))

                assertEquals(cached.name, result.name)
                assertEquals(cached.developer, result.developer)
                assertTrue(result.isInWishlist)
        }

        @Test
        fun `observeAppDetails EXPECT entity mapped to domain in flow`() = runTest {
                val e = entity(isInWishlist = true)
                whenever(dao.getAppDetails(e.id)).thenReturn(flowOf(e))

                val result = repository.observeAppDetails(e.id).first()
                assertEquals(e.name, result.name)
                assertTrue(result.isInWishlist)
        }

        @Test
        fun `observeAppDetails when dao emits null EXPECT empty flow`() = runTest {
                whenever(dao.getAppDetails("1")).thenReturn(flowOf(null))

                assertTrue(repository.observeAppDetails("1").toList().isEmpty())
        }

        @Test
        fun `toggleWishlist when currently false EXPECT dao called with true`() = runTest {
                val e = entity(isInWishlist = false)
                whenever(dao.getAppDetails(e.id)).thenReturn(flowOf(e))
                repository.toggleWishlist(e.id)

                verify(dao).updateWishlistStatus(e.id, true)
        }

        @Test
        fun `toggleWishlist when currently true EXPECT dao called with false`() = runTest {
                val e = entity(isInWishlist = true)
                whenever(dao.getAppDetails(e.id)).thenReturn(flowOf(e))
                repository.toggleWishlist(e.id)

                verify(dao).updateWishlistStatus(e.id, false)
        }

        @Test
        fun `toggleWishlist when entity not found EXPECT dao update never called`() = runTest {
                whenever(dao.getAppDetails("1")).thenReturn(flowOf(null))
                repository.toggleWishlist("1")

                verify(dao, never()).updateWishlistStatus(any(), any())
        }


        private fun dto(
                id: String = "1",
                name: String = "name",
                developer: String = "dev",
                ageRating: Int = 0,
                size: Double = 0.0,
                screenshotUrlList: List<String>? = null,
        ) = AppDetailsDto(
                id = id, name = name, developer = developer, category = "cat",
                ageRating = ageRating, size = size, iconUrl = "url",
                screenshotUrlList = screenshotUrlList, description = "desc",
        )

        private fun entity(
                id: String = "1",
                name: String = "name",
                developer: String = "dev",
                isInWishlist: Boolean = false,
        ) = AppDetailsEntity(
                id = id, name = name, developer = developer, category = "cat",
                ageRating = 0, size = 0.0, iconUrl = "url",
                screenshotUrlList = null, description = "decs",
                isInWishlist = isInWishlist,
        )
}
