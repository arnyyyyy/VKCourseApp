package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ObserveAppDetailsUseCaseTest {
        private lateinit var repository: AppRepository
        private lateinit var useCase: ObserveAppDetailsUseCase

        @Before
        fun setUp() {
                repository = mock()
                useCase = ObserveAppDetailsUseCase(repository)
        }

        @Test
        fun `invoke EXPECT flow with correct fully filled domain data`() = runTest {
                val id = "1"
                val app = AppDetails(
                        id = id, name = "VK", developer = "VK Team",
                        category = "aa", ageRating = 12, size = 11.7,
                        iconUrl = "https://example.com/icon.png",
                        screenshotUrlList = listOf("s1.png"),
                        description = "aa", isInWishlist = true,
                )
                whenever(repository.observeAppDetails(id)).thenReturn(flowOf(app))
                val result = useCase(id).first()
                assertEquals(app, result)
                verify(repository).observeAppDetails(id)
        }

        @Test
        fun `invoke with multiple emissions EXPECT all values collected in order`() = runTest {
                val v1 = app(id = "1", name = "a")
                val v2 = app(id = "2", name = "b")
                val v3 = app(id = "3", name = "c")
                whenever(repository.observeAppDetails(v1.id)).thenReturn(flowOf(v1, v2, v3))

                val results = useCase(v1.id).toList()

                assertEquals(3, results.size)
                assertEquals(v1.name, results[0].name)
                assertEquals(v2.name, results[1].name)
                assertEquals(v3.name, results[2].name)
        }

        @Test
        fun `invoke with wishlist toggled mid-stream EXPECT both states emitted`() = runTest {
                val before = app(isInWishlist = false)
                val after = app(isInWishlist = true)
                whenever(repository.observeAppDetails(before.id)).thenReturn(flowOf(before, after))

                val results = useCase(before.id).toList()

                assertEquals(before.isInWishlist, results[0].isInWishlist)
                assertEquals(after.isInWishlist, results[1].isInWishlist)
        }

        private fun app(
                id: String = "1",
                name: String = "name",
                isInWishlist: Boolean = false,
        ) = AppDetails(
                id = id, name = name, category = "cat", iconUrl = "url",
                description = "desc", isInWishlist = isInWishlist,
        )
}
